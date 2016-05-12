package workfinder.utils.ontology;

import java.io.InputStream;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.query.ResultSetRewindable;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SPARQLConnector {
    private SPARQLConnector() {
    }

    private static class SingletonHolder {
        private final static SPARQLConnector instance = new SPARQLConnector();
    }

    public static SPARQLConnector getInstance() {
        return SingletonHolder.instance;
    }

    private String ontologyFilePath = "workfinder.owl";
    private String ontologyBase = "http://www.semanticweb.org/pawel/ontologies/workfinder#";

    public String getOntologyBase() {
        return ontologyBase;
    }

    public String getOntologyFilePath() {
        return ontologyFilePath;
    }

    public void setOntologyFilePath(String ontologyFilePath) {
        this.ontologyFilePath = ontologyFilePath;
    }

    private String queryHeaders =
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                    + " PREFIX owl: <http://www.w3.org/2002/07/owl#>"
                    + "	PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                    + "	PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
                    + "	PREFIX wf: <http://www.semanticweb.org/pawel/ontologies/workfinder#> ";

    public OntModel connect() {
        Model base = null;
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, base);
        InputStream in = FileManager.get().open(ontologyFilePath);

        if (in == null) {
            throw new IllegalArgumentException("File not found");
        }

        return (OntModel) model.read(in, "");
    }

    public ResultSet excuteSparql(String queryString) {
        String queryWithHeaders = queryHeaders + queryString;
        Query query = QueryFactory.create(queryWithHeaders);
        QueryExecution queryExcution = QueryExecutionFactory.create(query, connect());

        ResultSet resultSet = queryExcution.execSelect();
        ResultSetRewindable resultSetTmp = ResultSetFactory.copyResults(resultSet);
        ResultSetFormatter.out(System.out, resultSetTmp);
        resultSetTmp.reset();

        return resultSetTmp;
    }
}
