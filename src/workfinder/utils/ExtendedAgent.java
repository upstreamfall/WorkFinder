package workfinder.utils;

import jade.core.Agent;

/**
 * Created by pbielicki on 04.05.2016.
 */
public class ExtendedAgent extends Agent {

    @Override
    protected void setup() {
        super.setup();
        printMessage("starts!");
    }

    @Override
    protected void takeDown() {
        super.takeDown();
        printMessage("ends!");
    }

    public String getAgentName() {
        return getAID().getLocalName();
    }

    public void printMessage(String message) {
        System.out.println("Agent " + getAgentName() + ": " + message);
    }
}
