package pl.edu.pw.mini.agents.workfinder;


import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.Random;

public class EmployerAgent extends Agent {

    private ProgrammerSkills simpleSkills;

    @Override
    protected void setup() {
        System.out.printf("Hello! I'm " + getAID().getName());

        simpleSkills = new ProgrammerSkills();
        simpleSkills.setSpecialization("Java");

        Random rand = new Random();
        simpleSkills.setExperienceYears(rand.nextInt(2));

        addBehaviour(new OfferJobBehaviour());
    }

    @Override
    protected void takeDown() {
        System.out.println(getAID() + " is shouting down!");
        super.takeDown();
    }

    private class OfferJobBehaviour extends Behaviour {

        private int stage = 0;
        private MessageTemplate mt;

        @Override
        public void action() {
            switch (stage) {
                case 0:
                    ACLMessage msg = myAgent.receive();
                    if (msg != null) {
                        if (msg.getPerformative() == ACLMessage.CFP) {
                            System.out.println("Agent " + getAID() + " receive a poor CV!");

                            String programmerSpec = msg.getContent();
                            if (programmerSpec.equals(simpleSkills.getSpecialization())) {
                                System.out.println("Found programmer with " + simpleSkills.getSpecialization() + " skills!");

                                ACLMessage reply = msg.createReply();
                                reply.setPerformative(ACLMessage.AGREE);
                                myAgent.send(reply);
                            }

                            stage = 1;
                            takeDown();
                        } else {
                            block();
                        }
                    }
                    break;
            }
        }

        @Override
        public boolean done() {
            return stage == 1;
        }
    }

}
