package pl.edu.pw.mini.agents.workfinder;

import jade.core.Agent;

/**
 * Created by Pawel on 2015-04-22.
 */
public class ExtendedAgent extends Agent {

    protected String getAgentName() {
        return getAID().getLocalName();
    }

    protected void printMessage(String message) {
        System.out.println("Agent " + getAgentName() + " " + message);
    }
}
