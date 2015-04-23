package pl.edu.pw.mini.agents.workfinder.employer;

import pl.edu.pw.mini.agents.workfinder.DFAgentManager;
import pl.edu.pw.mini.agents.workfinder.ExtendedAgent;
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
            addBehaviour(new OfferJobBehaviour(this));
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

    ProgrammerSkill getSimpleSkills() {
        return simpleSkills;
    }
}
