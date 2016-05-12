package workfinder.utils.ontology;

import jade.lang.acl.ACLMessage;
import workfinder.utils.ExtendedAgent;
import workfinder.utils.dto.Skill;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by pbielicki on 04.05.2016.
 */
public class GetOntologyDataAgent extends ExtendedAgent {
    private SPARQLConnector sparql = SPARQLConnector.getInstance();
    private UtilsQuery util = new UtilsQuery();

    private ACLMessage message;

    @Override
    protected void setup() {
        super.setup();

        Object[] args = getArguments();
        if (args != null && args.length == 1) {
            message = (ACLMessage) args[0];
            findOntology();
        }else {
            doDelete();
        }
    }

    private void findOntology() {
        String agentName = message.getSender().getLocalName();
        if (util.checkIfIndividualNameExists(agentName)){

            List<Skill> skills = util.getSkillsByProgrammerName(agentName);

            sendOntology(skills);
        } else {
            sendFailure();
        }
    }

    private void sendFailure() {
        ACLMessage response = message.createReply();
        response.setPerformative(ACLMessage.FAILURE);
        send(response);
    }

    private void sendOntology(List<Skill> skills) {
        ACLMessage response = message.createReply();
        response.setPerformative(ACLMessage.INFORM);

        try {
            response.setContentObject((Serializable) skills);
        } catch (IOException e) {
            e.printStackTrace();
        }
        send(response);
        doDelete();
    }
}
