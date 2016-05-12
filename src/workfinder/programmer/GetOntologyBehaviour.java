package workfinder.programmer;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import workfinder.utils.dto.Skill;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pbielicki on 04.05.2016.
 */
public class GetOntologyBehaviour extends OneShotBehaviour {
    private String conversationId;

    @Override
    public void action() {
        ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
        request.addReceiver(((ProgrammerAgent)myAgent).ontologyProxyAgency);
        String messageType = "GET_ONTOLOGY_DATA";
        conversationId = messageType + "-" + System.currentTimeMillis();
        request.setConversationId(conversationId);
        request.setContent(messageType);

        myAgent.addBehaviour(new HandleResponseBehaviour());
        myAgent.send(request);
    }

    private class HandleResponseBehaviour extends Behaviour {
        private boolean isDone;

        @Override
        public void action() {
            MessageTemplate messageTemplate = MessageTemplate.MatchConversationId(conversationId);
            ACLMessage response = myAgent.receive(messageTemplate);
            if (response != null) {
                if (response.getPerformative() == ACLMessage.INFORM) {
                    try {
                        Serializable contentObject = response.getContentObject();
                        List<Skill> skills = (List<Skill>) contentObject;
                        ((ProgrammerAgent) myAgent).printMessage("receives: " + skills);
                    } catch (UnreadableException e) {
                        e.printStackTrace();
                    }
                }else {
                    ((ProgrammerAgent) myAgent).printMessage("doesn't exist in ontology");
                }
                isDone = true;

            }else {
                block();
            }
        }

        @Override
        public boolean done() {
            return isDone;
        }
    }
}
