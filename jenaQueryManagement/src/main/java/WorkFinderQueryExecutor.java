import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Literal;

public class WorkFinderQueryExecutor {
	private SPARQLConnector sparql = SPARQLConnector.getInstance();
	
	public WorkFinderQueryExecutor(String ontologyFilePath){
		sparql.setOntologyFilePath(ontologyFilePath);
		sparql.connect();
	}
	
	public List<Triplet<String, String, Integer>> compareProgrammerWithJob(String programmerId, String jobId){
		String queryString =
			"SELECT  ?p ?s ((-1)*(?jlev - ?lev) AS ?diff)"
			+ "	WHERE { "
			+ "	?p rdf:type wf:Programmer ."
			+ "	?p wf:hasAuxSkill ?as ."
			+ "	?as wf:hasSkill ?s ."
			+ "	?as wf:hasLevel ?lev ."
			
			+ "	?job rdf:type wf:Job ."
			+ "	?job wf:hasAuxSkill ?jas ."
			+ "	?jas wf:hasSkill ?js ."
			+ "	?jas wf:hasLevel ?jlev ."
			
			+ "	FILTER ( ?s = ?js) ."
			+ "	FILTER ( ?jlev - ?lev <= 2 ) ."
			
			+ " FILTER (?p = wf:" + programmerId + ") ."
			+ " FILTER (?job = wf:" + jobId + ") ."
			+ "	}";
	 
		ResultSet results = sparql.excuteSparql(queryString);
		ResultSetFormatter.out(System.out, results);

		List<Triplet<String, String, Integer>> compareList = new ArrayList();
		while(results.hasNext()){
			QuerySolution querySolution = results.nextSolution();
			
			compareList.add(new Triplet<String, String, Integer>(
					querySolution.getLiteral("p").getString(), 
					querySolution.getLiteral("s").getString(), 
					Integer.parseInt(querySolution.getLiteral("diff").getString())));
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
		ResultSetFormatter.out(System.out, results);

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
		ResultSetFormatter.out(System.out, results);

		List<String> skillList = new ArrayList();
		while(results.hasNext()){
			QuerySolution querySolution = results.nextSolution();
			
			skillList.add(querySolution.getLiteral("technology").toString());
		}
		
		return skillList;	
	}
	
	public Map<String, String> getProgrammerInfo(String programmerId) {        
        String queryString =
				"SELECT *"
				+ " WHERE {" 
				+ "	?p rdf:type wf:Programmer ."	
				+ " ?p wf:hasEmail ?s ."
				+ " FILTER ( ?p = wf:" + programmerId + ") ."
				+ "	}";
		 
		ResultSet results = sparql.excuteSparql(queryString);
		System.out.println(results.getRowNumber());
		ResultSetFormatter.out(System.out, results);

		Map<String, String> infoList = new HashMap<String, String>();
		if(results.hasNext()){
			QuerySolution querySolution = results.nextSolution();
			infoList.put("name", programmerId);
			infoList.put("email", querySolution.getLiteral("email").toString());
		}
		
		return infoList;	
	}
	
	public void createNewProgrammer(String name) {
		OntModel ontModel = sparql.connect();
		
		OntClass programmerClass = ontModel.getOntClass(sparql.getOntologyBase() + "Programmer");
		Individual programmer = ontModel.createIndividual(sparql.getOntologyBase() + name, programmerClass);
		System.out.println(programmer.toString());
		sparql.saveModel(ontModel);
	}
}
