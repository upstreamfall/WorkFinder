package pl.edu.pw.mini.agents.workfinder;

import jade.core.AID;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class Test {
    public static void main(String[] arg) throws StaleProxyException {

        Runtime runtime = Runtime.instance();

        Profile profile = new ProfileImpl("172.17.42.1", 1099, null);

        ContainerController agentContainer = runtime.createAgentContainer(profile);

        Object reference = new Object();
        Object args[] = new Object[1];
        args[0] = reference;
        AgentController receiver = agentContainer.createNewAgent("Receiver", "pl.edu.pw.mini.agents.workfinder.Receiver", args);
        AgentController postman = agentContainer.createNewAgent("Postman", "pl.edu.pw.mini.agents.workfinder.Postman", args);
        receiver.start();
        postman.start();

        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        AID aid = new AID("Receiver", false);
        message.addReceiver(aid);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        postman.putO2AObject(message, true);
    }
}
