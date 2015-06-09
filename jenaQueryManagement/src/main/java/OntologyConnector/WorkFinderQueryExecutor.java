package OntologyConnector;

import java.util.*;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.*;
import dto.JobDTO;
import dto.ProgrammerDTO;
import dto.SkillDTO;
import dto.WorkerDTO;

public class WorkFinderQueryExecutor {
    private SPARQLConnector sparql = SPARQLConnector.getInstance();
    private String ontBase = sparql.getOntologyBase();
    private UtilsQuery utils = new UtilsQuery();

    public WorkFinderQueryExecutor(String ontologyFilePath) {
        sparql.setOntologyFilePath(ontologyFilePath);
    }

    public Map<String, Integer> compareProgrammerWithJob(String programmerId, String jobId) {
        String queryString =
                "SELECT  ?p ?s ((-1)*(?jlev - ?lev) AS ?diff)"
                        + "	WHERE { "
                        + "	?programmer rdf:type wf:Programmer ."
                        + "	?programmer wf:hasAuxSkill ?auxSkill ."
                        + "	?auxSkill wf:hasSkill ?skill ."
                        + "	?auxSkill wf:hasLevel ?level ."

                        + "	?job rdf:type wf:Job ."
                        + "	?job wf:hasAuxSkill ?jobAuxSkill ."
                        + "	?jobAuxSkill wf:hasSkill ?jobSkill ."
                        + "	?jobAuxSkill wf:hasLevel ?jobLevel ."

                        + "	FILTER ( ?skill = ?jobSkill) ."
                        + "	FILTER ( ?jobLevel - ?level <= 2 ) ."

                        + " FILTER (?programmer = wf:" + programmerId + ") ."
                        + " FILTER (?job = wf:" + jobId + ") ."
                        + "	}";

        ResultSet results = sparql.excuteSparql(queryString);

        Map<String, Integer> compareList = new HashMap<>();
        while (results.hasNext()) {
            QuerySolution querySolution = results.nextSolution();
            compareList.put(querySolution.getLiteral("p").getString(),
                    querySolution.getLiteral("diff").getInt());
        }

        return compareList;
    }

    /**
     * Get primary information about programmer without skills.
     *
     * @param programmerId
     * @return
     */
    public ProgrammerDTO getProgrammerDataInfo(String programmerId) {
        String queryString =
                "SELECT *"
                        + " WHERE {"
                        + "	?programmer rdf:type wf:Programmer ."
                        + " OPTIONAL { ?programmer wf:hasEmail ?email ."
                        + " }"
                        + " FILTER ( ?programmer = wf:" + programmerId + ") ."
                        + "	}";

        ResultSet results = sparql.excuteSparql(queryString);

        ProgrammerDTO infoList = new ProgrammerDTO();
        if (results.hasNext()) {
            QuerySolution querySolution = results.next();
            infoList.setName(querySolution.getResource("programmer").getLocalName());
            infoList.setEmail(querySolution.getLiteral("email").getString());
        }

        return infoList;
    }

    /**
     * Get worker list of skills.
     *
     * @param workerName
     * @return
     */
    public List<SkillDTO> getWorkerSkillsInfo(String workerName) {
        String queryString =
                "SELECT ?skill ?level ?priority "
                        + " WHERE {"
                        + " ?worker wf:hasAuxSkill ?auxSkill ."
                        + " ?auxSkill wf:hasSkill ?skill ."
                        + " ?auxSkill wf:hasLevel ?level ."
                        + " OPTIONAL { ?auxSkill wf:hasPriority ?priority }."
                        + " FILTER ( ?worker = wf:" + workerName + ") ."
                        + "	}";

        ResultSet results = sparql.excuteSparql(queryString);

        List<SkillDTO> infoList = new LinkedList<>();
        if (results.hasNext()) {
            QuerySolution querySolution = results.next();
            SkillDTO skill = new SkillDTO(querySolution.getResource("skill").getLocalName(),
                    querySolution.getLiteral("level").getInt());

            if (querySolution.contains("priority")) {
                skill.setPriority(querySolution.getLiteral("level").getInt());
            }
            infoList.add(skill);
        }

        return infoList;
    }

    public boolean createNewWorker(WorkerDTO newWorker) {
        if (utils.checkIfIndividualNameExists(newWorker.getName())) return false;

        switch (newWorker.getClass().getName()) {
            case "dto.ProgrammerDTO":
                createNewProgrammerModel((ProgrammerDTO) newWorker);
                break;
            case "dto.JobDTO":
                createNewJobModel((JobDTO) newWorker);
                break;
        }
        return true;
    }

    private void createNewProgrammerModel(ProgrammerDTO newProgrammer) {
        OntModel ontModel = sparql.connect();

        OntClass programmerClass = ontModel.getOntClass(ontBase + "Programmer");
        Individual programmer = ontModel.createIndividual(ontBase + newProgrammer.getName(), programmerClass);
        Property emailProperty = ontModel.createProperty(ontBase + "hasEmail");
        programmer.addProperty(emailProperty, newProgrammer.getEmail());

        addSkills(newProgrammer.getName(), newProgrammer.getSkillList());

        sparql.saveModel(ontModel);
    }

    private void createNewJobModel(JobDTO newJob) {
        OntModel ontModel = sparql.connect();

        OntClass jobClass = ontModel.getOntClass(ontBase + "Job");
        Individual job = ontModel.createIndividual(ontBase + newJob.getName(), jobClass);

        addSkills(newJob.getName(), newJob.getSkillList());

        sparql.saveModel(ontModel);
    }

    public void addSkill(String workerName, SkillDTO skill) {
        List<SkillDTO> skillDTOList = new LinkedList<>();
        skillDTOList.add(skill);
        addSkills(workerName, skillDTOList);
    }

    public void addSkills(String workerName, List<SkillDTO> skillList) {
        if (skillList != null) {
            OntModel ontModel = sparql.connect();
            Individual worker = ontModel.getIndividual(ontBase + workerName);
            for (SkillDTO skill : skillList) {
                addSkillToWorker(worker, skill);
            }
            sparql.saveModel(ontModel);
        }
    }

    private void addSkillToWorker(Individual worker, SkillDTO skill){
        OntModel ontModel = sparql.connect();
        OntClass auxSkillClass = ontModel.getOntClass(ontBase + "Auxskill");
        Individual auxSkill = ontModel.createIndividual(auxSkillClass);
        auxSkill.addProperty(ontModel.createProperty(ontBase + "hasLevel"), String.valueOf(skill.getLevel()));

        if (skill.getPriority() >= 0) {
            auxSkill.addProperty(ontModel.createProperty(ontBase + "hasPriority"), String.valueOf(skill.getPriority()));
        }

        worker.addProperty(ontModel.createProperty(ontBase + "hasAuxSkill"), auxSkill);
    }

    /**
     * Removes existing worker model.
     *
     * @param workerName
     * @return
     */
    public boolean deleteWorker(String workerName) {
        if (!utils.checkIfIndividualNameExists(workerName)) return false;

        OntModel ontModel = sparql.connect();

        Individual worker = ontModel.getIndividual(ontBase + workerName);
        worker.remove();
        sparql.saveModel(ontModel);

        return true;
    }
}
