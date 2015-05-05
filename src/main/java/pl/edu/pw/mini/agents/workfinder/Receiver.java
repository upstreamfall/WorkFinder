package pl.edu.pw.mini.agents.workfinder;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class Receiver extends ExtendedAgent {

    protected void setup() {
        //Write the greeting
        System.out.println("Hello! I'm " + getAgentName());
        //Add the simple behaviour
        addBehaviour(new ReceiverBehaviour(this));
    }

    class ReceiverBehaviour extends CyclicBehaviour {
        public ReceiverBehaviour(Agent a) {
            super(a);
        }

        public void action() {
            ACLMessage msg = receive();
            if (msg != null) {
                System.out.println("GET: " + msg);
            }
        }
    }
}
