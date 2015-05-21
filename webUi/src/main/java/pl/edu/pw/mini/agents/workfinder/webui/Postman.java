package pl.edu.pw.mini.agents.workfinder.webui;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class Postman extends Agent {
    protected void setup() {
        System.out.println("Hello! I'm " + getName());
        addBehaviour(new GetOBehaviour(this));
    }

    class GetOBehaviour extends CyclicBehaviour {
        public GetOBehaviour(Agent a) {
            super(a);
        }

        public void action() {
            setEnabledO2ACommunication(true, 1);
            Object ExObj = getO2AObject();
            if (ExObj != null) {
                System.out.println("Message to delivery: " + ExObj.toString());
                send((ACLMessage) ExObj);
            }
        }
    }
}