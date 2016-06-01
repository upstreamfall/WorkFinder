package workfinder.ontology;

import jade.lang.acl.ACLMessage;
import workfinder.utils.ExtendedAgent;
import workfinder.utils.dto.Skill;
import workfinder.utils.ontology.SPARQLConnector;
import workfinder.utils.ontology.UtilsQuery;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by pbielicki on 04.05.2016.
 */
public class OntologyDataAgent extends ExtendedAgent {
    protected SPARQLConnector sparql = SPARQLConnector.getInstance();
    protected UtilsQuery util = new UtilsQuery();

    protected ACLMessage message;

    @Override
    protected void setup() {
        super.setup();
    }

    protected void sendFailure() {
        ACLMessage response = message.createReply();
        response.setPerformative(ACLMessage.FAILURE);
        send(response);
    }

    protected void sendSuccess(Object result) {
        ACLMessage response = message.createReply();
        response.setPerformative(ACLMessage.INFORM);

        try {
            response.setContentObject((Serializable) result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        send(response);
        doDelete();
    }
}
