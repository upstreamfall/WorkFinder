package workfinder.programmer;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import workfinder.utils.dto.GlobalParameters;
import workfinder.utils.dto.Skill;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pbielicki on 04.05.2016.
 */
public class GetOntologyBehaviour extends OneShotBehaviour {
    private String conversationId;
    private ProgrammerAgent agent;

    @Override
    public void action() {
        agent = ((ProgrammerAgent)myAgent);
        ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
        request.addReceiver(GlobalParameters.getInstance().ontologyProxyAgency);
        String messageType = "GET_SKILLS";
        conversationId = messageType + "-" + System.currentTimeMillis();
        request.setConversationId(conversationId);
        request.setContent(messageType);

        agent.addBehaviour(new HandleResponseBehaviour());
        agent.send(request);
    }

    private class HandleResponseBehaviour extends Behaviour {
        private boolean isDone;

        @Override
        public void action() {
            MessageTemplate messageTemplate = MessageTemplate.MatchConversationId(conversationId);
            ACLMessage response = agent.receive(messageTemplate);
            if (response != null) {
                if (response.getPerformative() == ACLMessage.INFORM) {
                    try {
                        Serializable contentObject = response.getContentObject();
                        List<Skill> skills = (List<Skill>) contentObject;
                        agent.saveSkillList(skills);
                    } catch (UnreadableException e) {
                        e.printStackTrace();
                    }
                }else {
                    agent.printMessage("doesn't exist in ontology");
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
