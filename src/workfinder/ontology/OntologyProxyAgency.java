package workfinder.ontology;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import org.apache.commons.lang3.text.WordUtils;
import workfinder.utils.ExtendedAgent;

/**
 * Created by pbielicki on 04.05.2016.
 */
public class OntologyProxyAgency extends ExtendedAgent {
    String packagePath = this.getClass().getPackage().getName();

    @Override
    protected void setup() {
        super.setup();

        addBehaviour(new RequestHandlerBehaviour());
    }

    private class RequestHandlerBehaviour extends CyclicBehaviour {
        @Override
        public void action() {
            ACLMessage message = receive();
            if (message != null) {
                createAgentToAnswer(message);
            }else {
                block();
            }
        }
    }

    private void createAgentToAnswer(ACLMessage message) {
        ContainerController containerController = getContainerController();
        AgentController agentController;

        String content = message.getContent();
        String agentClassName = WordUtils.capitalizeFully(content, new char[]{'_'}).replaceAll("_", "");
        String agentName = "OntoAgent_" + agentClassName + "_" + System.currentTimeMillis();
        Object[] params = new Object[]{message};
        try {
            agentController = containerController.createNewAgent(
                    agentName, packagePath + "." + agentClassName + "Agent", params);
            agentController.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
            printMessage("received unsupported message type: " + content);
        }
    }
}
