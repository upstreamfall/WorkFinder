package pl.edu.pw.mini.agents.workfinder.programmer;

import jade.domain.FIPAAgentManagement.DFAgentDescription;
import pl.edu.pw.mini.agents.workfinder.DFAgentManager;
import pl.edu.pw.mini.agents.workfinder.ExtendedAgent;
import pl.edu.pw.mini.agents.workfinder.cvdata.PersonData;
import pl.edu.pw.mini.agents.workfinder.cvdata.ProgrammerSkill;

public class ProgrammerAgent extends ExtendedAgent {

    private ProgrammerSkill simpleSkills;
    private PersonData personData;

    private DFAgentDescription[] employers = new DFAgentDescription[0];
    private DFAgentManager dfAgentManager = new DFAgentManager();

    @Override
    protected void setup() {
        System.out.println("Hello! I'm " + getAgentName());
        addBehaviour(new ProgrammerStartupBehaviour(this));
    }

    @Override
    protected void takeDown() {
        printMessage("is shouting down!");
        super.takeDown();
    }

    public ProgrammerSkill getSimpleSkills() {
        return simpleSkills;
    }

    public void setSimpleSkills(ProgrammerSkill simpleSkills) {
        this.simpleSkills = simpleSkills;
    }

    public void setPersonData(PersonData personData) {
        this.personData = personData;
    }

    public DFAgentDescription[] getEmployers() {
        return employers;
    }

    public void setEmployers(DFAgentDescription[] employers) {
        this.employers = employers;
    }

    public DFAgentManager getDfAgentManager() {
        return dfAgentManager;
    }
}
