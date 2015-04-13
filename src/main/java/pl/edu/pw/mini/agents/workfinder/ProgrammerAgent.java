package pl.edu.pw.mini.agents.workfinder;


import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ProgrammerAgent extends Agent {

    private ProgrammerSkills simpleSkills;

    private AID employer = new AID("employer1", AID.ISLOCALNAME);

    @Override
    protected void setup() {
        System.out.printf("Hello! I'm " + getAID().getName());

        simpleSkills = new ProgrammerSkills();
        simpleSkills.setSpecialization("Java");
        simpleSkills.setExperienceYears(2);

        addBehaviour(new FindJobBehaviour(employer));
    }

    @Override
    protected void takeDown(){
        System.out.println(getAID() + " is shouting down!");
        super.takeDown();
    }


    private class FindJobBehaviour extends Behaviour {

        private int stage = 0;
        private AID employer;
        private MessageTemplate mt;

        public FindJobBehaviour(AID employer){
            this.employer = employer;
        }

        @Override
        public void action() {
            switch (stage){
                case 0:
                    ACLMessage callForJob = new ACLMessage(ACLMessage.CFP);
                    callForJob.addReceiver(employer);
                    callForJob.setContent(simpleSkills.getSpecialization());
                    callForJob.setConversationId("testId");
                    callForJob.setReplyWith("job" + System.currentTimeMillis());
                    myAgent.send(callForJob);

                    mt = MessageTemplate.and(MessageTemplate.MatchConversationId("testId"),
                            MessageTemplate.MatchInReplyTo(callForJob.getReplyWith()));

                    stage = 1;
                    break;
                case 1:
                    ACLMessage reply = myAgent.receive(mt);
                    if (reply != null) {
                        if (reply.getPerformative() == ACLMessage.AGREE) {
                            System.out.println("Agent " + getAID() + " gets a new job!");
                        }
                        else if (reply.getPerformative() == ACLMessage.CANCEL) {
                            System.out.println("Agent " + getAID() + " is to weak to get a good job!");
                        }

                        stage = 2;
                        takeDown();
                    }
                    else {
                        block();
                    }
                    break;
            }
        }

        @Override
        public boolean done() {
            return stage == 2;
        }
    }
}
