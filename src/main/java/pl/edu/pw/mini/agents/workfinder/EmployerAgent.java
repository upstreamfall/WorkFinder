package pl.edu.pw.mini.agents.workfinder;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import pl.edu.pw.mini.agents.workfinder.cvdata.ProgrammerSkill;

import java.util.Random;

public class EmployerAgent extends ExtendedAgent {

    private ProgrammerSkill simpleSkills;
    private DFAgentManager dfAgentManager = new DFAgentManager();

    @Override
    protected void setup() {
        System.out.println("Hello! I'm " + getAID().getName());

        simpleSkills = new ProgrammerSkill();
        simpleSkills.setSpecialization("JAVA");
        Random rand = new Random();
        simpleSkills.setExperienceYears(rand.nextInt(2));

        boolean result = dfAgentManager.register(this, "job-offering", "best-employer");
        if (result) {
            printMessage("successful register offers!");
            addBehaviour(new OfferJobBehaviour());
        } else {
            printMessage("registering job offers unsuccessful!");
            takeDown();
        }
    }

    @Override
    protected void takeDown() {
        printMessage("is shouting down!");
        dfAgentManager.deregister(this);

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
                        printMessage("receive message: " + msg.getContent());
                        if (msg.getPerformative() == ACLMessage.CFP) {
                            String programmerSpec = msg.getContent();
                            ACLMessage reply = msg.createReply();
                            if (programmerSpec.equals(simpleSkills.getSpecialization())) {
                                printMessage("found programmer with " + simpleSkills.getSpecialization() + " skills!");
                                reply.setPerformative(ACLMessage.AGREE);
                            }
                            else {
                                printMessage("receive a poor CV!");
                                reply.setPerformative(ACLMessage.REFUSE);
                            }
                            myAgent.send(reply);
//                            stage = 1;
//                            takeDown();
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
