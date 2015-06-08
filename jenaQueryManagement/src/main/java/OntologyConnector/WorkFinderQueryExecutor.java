package OntologyConnector;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.*;
import dto.ProgrammerDTO;

public class WorkFinderQueryExecutor {
	private SPARQLConnector sparql = SPARQLConnector.getInstance();
	
	public WorkFinderQueryExecutor(String ontologyFilePath){
		sparql.setOntologyFilePath(ontologyFilePath);
	}
	
//	public List<Triplet<String, String, Integer>> compareProgrammerWithJob(String programmerId, String jobId){
//		String queryString =
//			"SELECT  ?p ?s ((-1)*(?jlev - ?lev) AS ?diff)"
//			+ "	WHERE { "
//			+ "	?p rdf:type wf:Programmer ."
//			+ "	?p wf:hasAuxSkill ?as ."
//			+ "	?as wf:hasSkill ?s ."
//			+ "	?as wf:hasLevel ?lev ."
//
//			+ "	?job rdf:type wf:Job ."
//			+ "	?job wf:hasAuxSkill ?jas ."
//			+ "	?jas wf:hasSkill ?js ."
//			+ "	?jas wf:hasLevel ?jlev ."
//
//			+ "	FILTER ( ?s = ?js) ."
//			+ "	FILTER ( ?jlev - ?lev <= 2 ) ."
//
//			+ " FILTER (?p = wf:" + programmerId + ") ."
//			+ " FILTER (?job = wf:" + jobId + ") ."
//			+ "	}";
//
//		ResultSet results = sparql.excuteSparql(queryString);
//
//		List<Triplet<String, String, Integer>> compareList = new ArrayList();
//		while(results.hasNext()){
//			QuerySolution querySolution = results.nextSolution();
//
//			compareList.add(new Triplet<String, String, Integer>(
//					querySolution.getLiteral("p").getString(),
//					querySolution.getLiteral("s").getString(),
//					Integer.parseInt(querySolution.getLiteral("diff").getString())));
//		}
//
//		return compareList;
//	}
	
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
                        + "	?p rdf:type wf:Programmer ."
                        + " FILTER ( ?p = wf:" + name + ") ."
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

}
