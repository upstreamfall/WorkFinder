package workfinder.utils.ontology;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFactory;
import com.hp.hpl.jena.query.ResultSetRewindable;
import workfinder.utils.dto.Person;
import workfinder.utils.dto.Skill;

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

    public List<Skill> getSkillsByProgrammerName(String agentName) {
        String queryString =
                "SELECT ?skill ?level "
                        + "WHERE { ?subject wf:hasAuxSkill ?auxSkill."
                        + "?auxSkill wf:hasSkill ?skill."
                        + "?auxSkill wf:hasLevel ?level."
                        + "FILTER ( ?subject = wf:"+agentName+" )."
                        + "}";

        ResultSet results = sparql.excuteSparql(queryString);

        List<Skill> skillList = new ArrayList();
        while (results.hasNext()) {
            QuerySolution querySolution = results.nextSolution();

            skillList.add(new Skill(querySolution.getResource("skill").getLocalName(),
                    querySolution.getLiteral("level").getInt()));
        }

        return skillList;
    }

    public Person getPersonData(String personName) {
        String queryString =
                "SELECT ?skill ?level "
                        + "WHERE { ?subject wf:hasAuxSkill ?auxSkill."
                        + "?auxSkill wf:hasSkill ?skill."
                        + "?auxSkill wf:hasLevel ?level."
                        + "FILTER ( ?subject = wf:"+personName+" )."
                        + "}";

        ResultSet results = sparql.excuteSparql(queryString);
        while (results.hasNext()) {
            QuerySolution querySolution = results.nextSolution();

//            skillList.add(new Skill(querySolution.getResource("skill").getLocalName(),
//                    querySolution.getLiteral("level").getInt()));
        }

        return null;
    }
}
