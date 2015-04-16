package pl.edu.pw.mini.agents.workfinder;


import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import pl.edu.pw.mini.agents.workfinder.cvdata.ProgrammerSkill;

public class ProgrammerAgent extends Agent {

    private ProgrammerSkill simpleSkills;

    private DFAgentDescription[] employers = new DFAgentDescription[0];
    private DFAgentManager dfAgentManager = new DFAgentManager();

    @Override
    protected void setup() {
        System.out.println("Hello! I'm " + getAID().getName());

        simpleSkills = new ProgrammerSkill();
        simpleSkills.setSpecialization("Java");
        simpleSkills.setExperienceYears(2);

        addBehaviour(new WakerBehaviour(this, 1000) {
            @Override
            protected void onWake() {
                addBehaviour(new FindJobBehaviour());
            }
        });
    }

    @Override
    protected void takeDown(){
        System.out.println(getAID() + " is shouting down!");
        super.takeDown();
    }


    private class FindJobBehaviour extends Behaviour {

        private int stage = 0;
        private MessageTemplate mt;

        @Override
        public void action() {
            switch (stage){
                case 0:
                    employers = dfAgentManager.searchForService(myAgent, "job-offering");
                    if (employers!= null && employers.length > 0 ) {
                        stage++;
                    }
                    break;
                case 1:
                    ACLMessage callForJob = new ACLMessage(ACLMessage.CFP);
                    for (DFAgentDescription agentDescription : employers){
                        callForJob.addReceiver(agentDescription.getName());
                    }
                    callForJob.setContent(simpleSkills.getSpecialization());
                    callForJob.setConversationId("testId");
                    callForJob.setReplyWith("job" + System.currentTimeMillis());
                    myAgent.send(callForJob);

                    mt = MessageTemplate.and(MessageTemplate.MatchConversationId("testId"),
                            MessageTemplate.MatchInReplyTo(callForJob.getReplyWith()));

                    stage++;
                    break;
                case 2:
                    ACLMessage reply = myAgent.receive(mt);
                    if (reply != null) {
                        if (reply.getPerformative() == ACLMessage.AGREE) {
                            System.out.println("Agent " + getAID() + " gets a new job!");
                        }
                        else if (reply.getPerformative() == ACLMessage.CANCEL) {
                            System.out.println("Agent " + getAID() + " is to weak to get a good job!");
                        }

                        stage++;
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
            return stage == 3;
        }
    }
}
