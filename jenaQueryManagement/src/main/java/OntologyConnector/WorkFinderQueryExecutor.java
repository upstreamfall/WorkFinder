package OntologyConnector;

import java.util.*;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.*;
import dto.ProgrammerDTO;
import dto.SkillDTO;

public class WorkFinderQueryExecutor {
	private SPARQLConnector sparql = SPARQLConnector.getInstance();
	
	public WorkFinderQueryExecutor(String ontologyFilePath){
		sparql.setOntologyFilePath(ontologyFilePath);
	}
	
	public Map<String, Integer> compareProgrammerWithJob(String programmerId, String jobId){
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
		while(results.hasNext()){
			QuerySolution querySolution = results.nextSolution();
            compareList.put(querySolution.getLiteral("p").getString(),
                            querySolution.getLiteral("diff").getInt());
		}

		return compareList;
	}
	
	public List<String> getSkillRoots(){
		return getSubSkills("Skill");
	}
	
	public List<String> getSubSkills(String skillName){
		String queryString =
			"SELECT (?directSub AS ?technology) ?super"
			+ " WHERE {" 
			+ " ?directSub rdfs:subClassOf ?super ."
			+ " FILTER NOT EXISTS {"
			+ "				?otherSub rdfs:subClassOf ?super ."
			+ "				?directSub rdfs:subClassOf ?otherSub . "
			+ "				FILTER ( ?directSub != ?otherSub ) ."
			+ " }"
			+ " FILTER ( ?super = wf:" + skillName + " ) ."
			+ "	}";

        ResultSet results = sparql.excuteSparql(queryString);
		List<String> skillList = new ArrayList();
		while(results.hasNext()){
			QuerySolution querySolution = results.nextSolution();
			
			skillList.add(querySolution.getLiteral("technology").toString());
		}
		
		return skillList;
	}

	public List<String> getSkillIndividuals(String skillName) {
		String queryString =
				"SELECT ?technology"
				+ " WHERE {" 
				+ " ?technology rdf:type wf:" + skillName
				+ "	}";
		 
		ResultSet results = sparql.excuteSparql(queryString);

		List<String> skillList = new ArrayList();
		while(results.hasNext()){
			QuerySolution querySolution = results.nextSolution();
			
			skillList.add(querySolution.getLiteral("technology").toString());
		}
		
		return skillList;	
	}

    /**
     * Get primary information about programmer.
     * @param programmerId
     * @return
     */
	public ProgrammerDTO getProgrammerInfo(String programmerId) {
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
     * Creates new programmer model.
     * @param programmer
     */
	public boolean createNewProgrammer(ProgrammerDTO programmer) {
        if(!checkIfProgrammerNameExists(programmer.getName()) )  return false;

        createNewProgrammerModel(programmer);
        return true;
	}

    private void createNewProgrammerModel(ProgrammerDTO programmerDto) {
        OntModel ontModel = sparql.connect();
        String ontBase = sparql.getOntologyBase();

        OntClass programmerClass = ontModel.getOntClass(ontBase + "Programmer");
        Individual programmer = ontModel.createIndividual(ontBase + programmerDto.getName(), programmerClass);
        Property emailProperty = ontModel.createProperty(ontBase + "hasEmail");
        programmer.addProperty(emailProperty, programmerDto.getEmail());

        sparql.saveModel(ontModel);
    }

    private boolean checkIfProgrammerNameExists(String name) {
        String queryString =
                "SELECT *"
                        + " WHERE {"
                        + "	?programmer rdf:type wf:Programmer ."
                        + " FILTER ( ?programmer = wf:" + name + ") ."
                        + "	}";

        ResultSet results = sparql.excuteSparql(queryString);

        return results.getRowNumber() != 1;
    }

    /**
     * Removes existing programmer model.
     * @param name
     * @return
     */
    public boolean deleteProgrammer(String name){
        if(!checkIfProgrammerNameExists(name) )  return false;

        OntModel ontModel = sparql.connect();
        String ontBase = sparql.getOntologyBase();

        OntClass programmerClass = ontModel.getOntClass(ontBase + "Programmer");
        Individual programmer = ontModel.createIndividual(ontBase + name, programmerClass);
        programmer.remove();

        sparql.saveModel(ontModel);

        return true;
    }

    public void addSkill(String name, SkillDTO skill){
        OntModel ontModel = sparql.connect();
        String ontBase = sparql.getOntologyBase();

        Individual programmer = ontModel.getIndividual(ontBase + name);

        OntClass auxSkillClass = ontModel.getOntClass(ontBase + "Auxskill");
        Individual auxSkill = ontModel.createIndividual(auxSkillClass);
        auxSkill.addProperty(ontModel.createProperty(ontBase + "hasLevel"), String.valueOf(skill.getLevel()));

        programmer.addProperty(ontModel.createProperty(ontBase + "hasAuxSkill"), auxSkill);
        sparql.saveModel(ontModel);
    };

    public List<SkillDTO> getProgrammerSkills(String name){
        List<SkillDTO> skillList = new LinkedList<>();

        String queryString =
                "SELECT ?skill ?level"
                        + " WHERE {"
                        + " ?programmer rdf:type wf:Programmer ."
                        + " ?programmer wf:hasAuxSkill ?auxSkill ."
                        + " ?auxSkill wf:hasSkill ?skill ."
                        + " ?auxSkill wf:hasLevel ?level ."
                        + " FILTER ( ?programmer = wf:" + name + " ) ."
                        + "	}";

        ResultSet results = sparql.excuteSparql(queryString);
        while(results.hasNext()){
            QuerySolution querySolution = results.nextSolution();
            skillList.add(new SkillDTO(
                    querySolution.getResource("skill").getLocalName(),
                    querySolution.getLiteral("level").getInt(),
                    0));
        }

        return  skillList;
    };
}
