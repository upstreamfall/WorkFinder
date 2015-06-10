package OntologyConnector;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFactory;
import com.hp.hpl.jena.query.ResultSetRewindable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pbielicki on 2015-06-09.
 */
public class UtilsQuery {
    private SPARQLConnector sparql = SPARQLConnector.getInstance();

    public boolean checkIfIndividualNameExists(String name) {
        String queryString =
                "SELECT distinct *"
                        + " WHERE {"
                        + "	?subject rdf:type owl:NamedIndividual ."
                        + " FILTER ( ?subject = wf:" + name + ") ."
                        + "	}";

        ResultSet results = sparql.excuteSparql(queryString);
        ResultSetRewindable resultSetTmp = ResultSetFactory.copyResults(results);
        return resultSetTmp.size() == 1;
    }

    public List<String> getSkillRoots() {
        return getSubSkills("Skill");
    }

    public List<String> getSubSkills(String skillName) {
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
        while (results.hasNext()) {
            QuerySolution querySolution = results.nextSolution();

            skillList.add(querySolution.getResource("technology").getLocalName());
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
        while (results.hasNext()) {
            QuerySolution querySolution = results.nextSolution();

            skillList.add(querySolution.getResource("technology").getLocalName());
        }

        return skillList;
    }
}
