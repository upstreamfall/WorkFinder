package pl.edu.pw.mini.agents.workfinder.programmer;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.WakerBehaviour;
import pl.edu.pw.mini.agents.workfinder.cvdata.PersonData;
import pl.edu.pw.mini.agents.workfinder.cvdata.ProgrammerSkill;
import pl.edu.pw.mini.agents.workfinder.utils.PropertiesFileLoader;

import java.util.Properties;

class ProgrammerStartupBehaviour extends OneShotBehaviour {

    private ProgrammerAgent myAgent;

    public ProgrammerStartupBehaviour(Agent a) {
        super(a);
        this.myAgent = (ProgrammerAgent) a;
    }

    @Override
    public void action() {
        loadPersonalData(myAgent.getLocalName());
        loadSkills(myAgent.getLocalName());

        runAfterInitializeBehaviour();
    }

    private void loadSkills(String localName) {
        Properties prop = PropertiesFileLoader.loadSPropertyFile(localName, "skills");
        myAgent.setSimpleSkills(new ProgrammerSkill(prop));
    }

    private void loadPersonalData(String localName) {
        Properties prop = PropertiesFileLoader.loadSPropertyFile(localName, "personData");
        myAgent.setPersonData(new PersonData(prop));
    }

    private void runAfterInitializeBehaviour() {
        myAgent.addBehaviour(new WakerBehaviour(getAgent(), 1000) {
            @Override
            protected void onWake() {
                myAgent.addBehaviour(new FindJobBehaviour(myAgent));
            }
        });
    }
}
