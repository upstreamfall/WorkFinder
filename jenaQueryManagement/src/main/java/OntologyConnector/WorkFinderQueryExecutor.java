package OntologyConnector;

import java.util.*;
import java.util.function.BiConsumer;

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

    public Map<String, Integer> compareProgrammerWithJob(String programmerName, String jobName) {
        String queryString =
                "SELECT * WHERE {\n" +
                        "\t{\tSELECT  ?programmer ?skill (?jobPriority*(?levelevel - ?jobLevel) AS ?difference)\n" +
                        "\t\tWHERE { ?programmer rdf:type wf:Programmer .\n" +
                        "\t\t?programmer wf:hasAuxSkill ?auxSkill .\n" +
                        "\t\t?auxSkill wf:hasSkill ?skill .\n" +
                        "\t\t?auxSkill wf:hasLevel ?levelevel .\n" +
                        "\t\t?job rdf:type wf:Job .\n" +
                        "\t\t?job wf:hasAuxSkill ?jobAuxSkill .\n" +
                        "\t\t?jobAuxSkill wf:hasSkill ?jobSkill .\n" +
                        "\t\t?jobAuxSkill wf:hasLevel ?jobLevel .\n" +
                        "\t\t?jobAuxSkill wf:hasPriority ?jobPriority .\n" +
                        "\t\tFILTER ( ?skill = ?jobSkill) .\n" +
                        "\t\tFILTER (?programmer = wf:" + programmerName + ") .\n" +
                        "\t\tFILTER (?job = wf:" + jobName + ") .}\n" +
                        "\t}\n" +
                        "\tUNION \n" +
                        "\t{\tSELECT ?skill ((-1)*?jobPriority*?level AS ?difference) WHERE {\n" +
                        "\t\t{\tSELECT  ?skill ?level ?jobPriority\n" +
                        "\t\t\tWHERE { \n" +
                        "\t\t\t?job rdf:type wf:Job .\n" +
                        "\t\t\t?job wf:hasAuxSkill ?jobAuxSkill .\n" +
                        "\t\t\t?jobAuxSkill wf:hasSkill ?skill .\n" +
                        "\t\t\t?jobAuxSkill wf:hasLevel ?level .\n" +
                        "\t\t\t?jobAuxSkill wf:hasPriority ?jobPriority .\n" +
                        "\t\t\tFILTER (?job = wf:" + jobName + ") .}\n" +
                        "\t\t}\n" +
                        "\t\tMINUS {\n" +
                        "\t\t\tSELECT ?skill\n" +
                        "\t\t\tWHERE { \n" +
                        "\t\t\t?programmer rdf:type wf:Programmer .\n" +
                        "\t\t\t?programmer wf:hasAuxSkill ?auxSkill .\n" +
                        "\t\t\t?auxSkill wf:hasSkill ?skill .\n" +
                        "\t\t\tFILTER (?programmer = wf:"+ programmerName + ") . }\n" +
                        "\t\t\t}\n" +
                        "\t\t}\n" +
                        "\t}\n" +
                        "}";

        ResultSet results = sparql.excuteSparql(queryString);

        Map<String, Integer> compareList = new HashMap<>();
        while (results.hasNext()) {
            QuerySolution querySolution = results.nextSolution();
            compareList.put(querySolution.getResource("skill").getLocalName(),
                    querySolution.getLiteral("difference").getInt());
        }

        return compareList;
    }

    public int countProgrammerScore(String programmerName, String jobName) {
        final int[] score = {0};

        Map<String, Integer> compareList = compareProgrammerWithJob(programmerName, jobName);
        compareList.forEach(new BiConsumer<String, Integer>() {
            @Override
            public void accept(String s, Integer integer) {
                score[0] +=integer;
            }
        });

        return score[0];
    }

    /**
     * Get primary information about programmer without skills.
     *
     * @param programmerId
     * @return
     */
    public ProgrammerDTO getProgrammerDataInfo(String programmerName) {
        String queryString =
                "SELECT *"
                        + " WHERE {"
                        + "	?programmer rdf:type wf:Programmer ."
                        + " OPTIONAL { ?programmer wf:hasEmail ?email ."
                        + " }"
                        + " FILTER ( ?programmer = wf:" + programmerName + ") ."
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

    private void addSkillToWorker(Individual worker, SkillDTO skill) {
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
