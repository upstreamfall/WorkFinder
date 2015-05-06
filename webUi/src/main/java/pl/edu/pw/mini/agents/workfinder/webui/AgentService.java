package pl.edu.pw.mini.agents.workfinder.webui;

import jade.core.AID;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static java.util.UUID.randomUUID;

@Service
public class AgentService {

    private final AgentController postman;

    @Autowired
    public AgentService(
            @Value("${jade.host}") String host,
            @Value("${jade.port}") int port
    ) {
        jade.core.Runtime runtime = jade.core.Runtime.instance();
        Profile profile = new ProfileImpl(host, port, null);
        ContainerController agentContainer = runtime.createAgentContainer(profile);
        postman = findOrCreatePostman(agentContainer);
    }

    private AgentController findOrCreatePostman(ContainerController agentContainer) {
        AgentController agent;
        try {
            Object reference = new Object();
            Object args[] = new Object[1];
            args[0] = reference;
            String nickname = "Postman_" + randomUUID();
            agent = agentContainer.createNewAgent(nickname, "pl.edu.pw.mini.agents.workfinder.webui.Postman", args);
            agent.start();
        } catch (StaleProxyException e) {
            throw new RuntimeException(e);
        }
        return agent;
    }

    public void sendMessage(String receiverName) {
        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        AID aid = new AID(receiverName, false);
        message.addReceiver(aid);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            postman.putO2AObject(message, true);
        } catch (StaleProxyException e) {
            throw new RuntimeException(e);
        }
    }
}
