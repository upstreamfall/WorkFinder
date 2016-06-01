package workfinder.programmer;

import jade.core.AID;
import workfinder.utils.ExtendedAgent;
import workfinder.utils.dto.GlobalParameters;
import workfinder.utils.dto.Person;
import workfinder.utils.dto.Skill;

import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by pbielicki on 04.05.2016.
 */
public class ProgrammerAgent extends ExtendedAgent {
    private Person _person;
    private List<Skill> _skills;

    @Override
    protected void setup() {
        super.setup();
        configureParams();

        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        addBehaviour(new GetOntologyBehaviour());
    }

    private void configureParams() {
    }

    public void saveSkillList(List<Skill> skills) {
        this._skills = skills;
        printMessage("receives: " + skills);
    }
}
