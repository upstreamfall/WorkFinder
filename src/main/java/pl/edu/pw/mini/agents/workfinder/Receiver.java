package pl.edu.pw.mini.agents.workfinder;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class Receiver extends ExtendedAgent {

    private final static String REMOTE_URL = "http://localhost:8080/agent/%s/response";
    private final HttpClient client = HttpClientBuilder.create().build();

    protected void setup() {
        //Write the greeting
        System.out.println("Hello! I'm " + getAgentName());
        addBehaviour(new ReceiverBehaviour(this));
    }

    private void sentInfoToWebUi(String data) {
        HttpPost post = new HttpPost(String.format(REMOTE_URL, getAgentName()));
        try {
            post.setEntity(new StringEntity(data));
            client.execute(post);
        } catch (IOException e) {
            //This should never happened
            throw new RuntimeException(e);
        }
    }

    class ReceiverBehaviour extends CyclicBehaviour {
        public ReceiverBehaviour(Agent a) {
            super(a);
        }

        public void action() {
            ACLMessage msg = receive();
            if (msg != null) {
                System.out.println("GET: " + msg);
                sentInfoToWebUi(msg.toString());
            }
        }
    }
}
