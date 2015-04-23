package pl.edu.pw.mini.agents.workfinder.employer;


import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

class OfferJobBehaviour extends Behaviour {

    private int stage = 0;
    private MessageTemplate mt;
    private EmployerAgent myAgent;

    public OfferJobBehaviour(Agent a) {
        super(a);
        myAgent = (EmployerAgent) a;
    }

    @Override
    public void action() {
        switch (stage) {
            case 0:
                waitForProposal();
                break;
        }
    }

    private void waitForProposal() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            myAgent.printMessage("receive message: " + msg.getContent());
            if (msg.getPerformative() == ACLMessage.CFP) {
                String programmerSpec = msg.getContent();
                ACLMessage reply = msg.createReply();
                if (programmerSpec.equals(myAgent.getSimpleSkills().getSpecialization())) {
                    myAgent.printMessage("found programmer with " + myAgent.getSimpleSkills().getSpecialization() + " skills!");
                    reply.setPerformative(ACLMessage.AGREE);
                }
                else {
                    myAgent.printMessage("receive a poor CV!");
                    reply.setPerformative(ACLMessage.REFUSE);
                }
                myAgent.send(reply);
            } else {
                block();
            }
        }
    }

    @Override
    public boolean done() {
        return stage == 1;
    }
}