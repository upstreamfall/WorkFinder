package pl.edu.pw.mini.agents.workfinder;

import OntologyConnector.UtilsQuery;
import OntologyConnector.WorkFinderQueryExecutor;
import com.google.common.base.Joiner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dto.ProgrammerDTO;
import dto.SkillDTO;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.*;

public class Receiver extends ExtendedAgent {

    private final static String REMOTE_URL = "http://localhost:8080/agent/%s/response";
    private final HttpClient client = HttpClientBuilder.create().build();
    private final UtilsQuery utilsQuery = new UtilsQuery();
    private final WorkFinderQueryExecutor queryExecutor = new WorkFinderQueryExecutor("workfinder.owl");

    private final Gson gson = new GsonBuilder().create();

    protected void setup() {
        //Write the greeting
        System.out.println("Hello! I'm " + getAgentName());
        queryExecutor.createNewWorker(new ProgrammerDTO(getAgentName(), "tobe@developed.com", Collections.emptyList()));
        addBehaviour(new ReceiverBehaviour(this));
    }

    private void sentInfoToWebUi() {
        HttpPost post = new HttpPost(String.format(REMOTE_URL, getAgentName()));

        try {
            String json = gson.toJson(utilsQuery.getSkillRoots());
            post.setEntity(new StringEntity(json));
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
                SkillDTO skills = gson.fromJson(msg.getContent(), SkillDTO.class);
                System.out.println("GET: " + skills);
                queryExecutor.addSkill(getAgentName(), skills);
                sentInfoToWebUi();
            }
        }
    }
}
