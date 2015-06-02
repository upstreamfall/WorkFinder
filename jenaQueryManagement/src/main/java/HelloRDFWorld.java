import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class HelloRDFWorld {	
	public static void main(String[] args) {
		WorkFinderQueryExecutor wf = new WorkFinderQueryExecutor("workfinder.owl");
		
		SPARQLConnector sparql = SPARQLConnector.getInstance();
		sparql.connect();
		wf.compareProgrammerWithJob("Adam", "MobileDeveloper");
		wf.getSkillRoots();
		wf.getSubSkills("Developing");
		wf.getSubSkills("Database");
		wf.getSkillIndividuals("MobileTechnology");
		wf.getProgrammerInfo("Adam");
		wf.getProgrammerInfo("Sam");
		
		wf.createNewProgrammer("Sam23");
	}
	
	public void RDFtest() throws FileNotFoundException{
		InputStream in = new FileInputStream(new File("vc-db-1.rdf")); 
		Model model = ModelFactory.createMemModelMaker().createDefaultModel();
		model.read(in, null);
		
		String queryString = 
				"PREFIX rdf:<http://www.w3.org/2001/vcard-rdf/3.0#>"
				+ "SELECT * "
				+ "WHERE {"
				+ "[] rdf:Family ?fname ."
				+ "[] rdf:Given ?givenName ."
				+ "}";
		
		Query query = QueryFactory.create(queryString);
		QueryExecution queryExec = QueryExecutionFactory.create(query, model);
		ResultSet results = queryExec.execSelect();
		
		ResultSetFormatter.out(System.out, results, query);
		queryExec.close();
	}

}
