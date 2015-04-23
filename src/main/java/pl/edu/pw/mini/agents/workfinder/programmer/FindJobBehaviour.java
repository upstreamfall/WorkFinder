package pl.edu.pw.mini.agents.workfinder.programmer;

import jade.core.Agent;
import jade.core
        .behaviours.Behaviour;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

class FindJobBehaviour extends Behaviour {

    private int stage = 0;
    private MessageTemplate mt;
    private ProgrammerAgent myAgent;

    public FindJobBehaviour(Agent a) {
        super(a);
        myAgent = (ProgrammerAgent)a;
    }

    @Override
    public void action() {
        switch (stage) {
            case 0:
                searchForService();
                break;
            case 1:
                searchForJob();
                break;
            case 2:
                waitForJobStatus();
                break;
            case 3:
                stage++;
                break;
        }
    }

    private void waitForJobStatus() {
        ACLMessage reply = myAgent.receive(mt);
        if (reply != null) {
            if (reply.getPerformative() == ACLMessage.AGREE) {
                System.out.println("Agent " + myAgent.getName() + " gets a new job!");
            } else if (reply.getPerformative() == ACLMessage.CANCEL) {
                System.out.println("Agent " + myAgent.getName() + " is to weak to get a good job!");
            }
            stage++;
        } else {
            block();
        }
    }

    private void searchForJob() {
        ACLMessage callForJob = new ACLMessage(ACLMessage.CFP);
        for (DFAgentDescription agentDescription : myAgent.getEmployers()) {
            callForJob.addReceiver(agentDescription.getName());
        }
        callForJob.setContent(myAgent.getSimpleSkills().getSpecialization());
        callForJob.setConversationId("testId");
        callForJob.setReplyWith("job" + System.currentTimeMillis());
        myAgent.send(callForJob);

        mt = MessageTemplate.and(MessageTemplate.MatchConversationId("testId"),
                MessageTemplate.MatchInReplyTo(callForJob.getReplyWith()));

        stage++;
    }

    private void searchForService() {
        DFAgentDescription[] employers = myAgent.getDfAgentManager().searchForService(myAgent, "job-offering");
        myAgent.setEmployers(employers);
        if (employers != null && employers.length > 0) {
            stage++;
        }
    }

    @Override
    public boolean done() {
        return stage == 4;
    }
}