package workfinder.ontology;

import jade.lang.acl.ACLMessage;
import workfinder.utils.dto.Skill;

import java.util.List;

/**
 * Created by pbielicki on 04.05.2016.
 */
public class GetSkillsAgent extends OntologyDataAgent {
    @Override
    protected void setup() {
        super.setup();

        Object[] args = getArguments();
        if (args != null && args.length == 1) {
            message = (ACLMessage) args[0];
            findSkills();
        }else {
            doDelete();
        }
    }

    private void findSkills() {
        String agentName = message.getSender().getLocalName();
        if (util.checkIfIndividualNameExists(agentName)){
            List<Skill> skills = util.getSkillsByProgrammerName(agentName);
            sendSuccess(skills);
        } else {
            sendFailure();
        }
    }
}
