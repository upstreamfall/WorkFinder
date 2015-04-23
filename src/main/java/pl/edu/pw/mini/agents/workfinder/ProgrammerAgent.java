package pl.edu.pw.mini.agents.workfinder;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import pl.edu.pw.mini.agents.workfinder.cvdata.PersonData;
import pl.edu.pw.mini.agents.workfinder.cvdata.ProgrammerSkill;
import pl.edu.pw.mini.agents.workfinder.utils.PropertiesFileLoader;

import java.io.*;
import java.util.Properties;
import java.util.Random;

public class ProgrammerAgent extends ExtendedAgent {

    private ProgrammerSkill simpleSkills;
    private PersonData personData;

    private DFAgentDescription[] employers = new DFAgentDescription[0];
    private DFAgentManager dfAgentManager = new DFAgentManager();

    @Override
    protected void setup() {
        System.out.println("Hello! I'm " + getAgentName());
        addBehaviour(new InitilizeProgrammerBehaviour());
    }

    @Override
    protected void takeDown() {
        printMessage("is shouting down!");
        super.takeDown();
    }


    private class FindJobBehaviour extends Behaviour {

        private int stage = 0;
        private MessageTemplate mt;

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
                    takeDown();
                    break;
            }
        }

        private void waitForJobStatus() {
            ACLMessage reply = myAgent.receive(mt);
            if (reply != null) {
                if (reply.getPerformative() == ACLMessage.AGREE) {
                    System.out.println("Agent " + getAgentName() + " gets a new job!");
                } else if (reply.getPerformative() == ACLMessage.CANCEL) {
                    System.out.println("Agent " + getAgentName() + " is to weak to get a good job!");
                }

                stage++;
            } else {
                block();
            }
        }

        private void searchForJob() {
            ACLMessage callForJob = new ACLMessage(ACLMessage.CFP);
            for (DFAgentDescription agentDescription : employers) {
                callForJob.addReceiver(agentDescription.getName());
            }
            callForJob.setContent(simpleSkills.getSpecialization());
            callForJob.setConversationId("testId");
            callForJob.setReplyWith("job" + System.currentTimeMillis());
            myAgent.send(callForJob);

            mt = MessageTemplate.and(MessageTemplate.MatchConversationId("testId"),
                    MessageTemplate.MatchInReplyTo(callForJob.getReplyWith()));

            stage++;
        }

        private void searchForService() {
            employers = dfAgentManager.searchForService(myAgent, "job-offering");
            if (employers != null && employers.length > 0) {
                stage++;
            }
            return;
        }

        @Override
        public boolean done() {
            return stage == 4;
        }
    }

    private class InitilizeProgrammerBehaviour extends OneShotBehaviour {
        @Override
        public void action() {
            loadPersonalData(getLocalName());
            loadSkills(getLocalName());

            runAfterInitializeBehaviour();
        }

        private void loadSkills(String localName) {
            Properties prop = PropertiesFileLoader.loadSPropertyFile(localName, "skills");
            simpleSkills = new ProgrammerSkill(prop);
        }

        private void loadPersonalData(String localName) {
            Properties prop = PropertiesFileLoader.loadSPropertyFile(localName, "personData");
            personData = new PersonData(prop);
        }

        private void runAfterInitializeBehaviour() {
            addBehaviour(new WakerBehaviour(getAgent(), 1000) {
                @Override
                protected void onWake() {
                    addBehaviour(new FindJobBehaviour());
                }
            });
        }
    }
}
