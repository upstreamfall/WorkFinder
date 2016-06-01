package workfinder.ontology;

import jade.lang.acl.ACLMessage;
import workfinder.utils.dto.Person;
import workfinder.utils.dto.Skill;

import java.util.List;

/**
 * Created by pbielicki on 04.05.2016.
 */
public class GetPersonDataAgent extends OntologyDataAgent {
    private ACLMessage message;

    @Override
    protected void setup() {
        super.setup();

        Object[] args = getArguments();
        if (args != null && args.length == 1) {
            message = (ACLMessage) args[0];
            findPerson();
        }else {
            doDelete();
        }
    }

    private void findPerson() {
        String agentName = message.getSender().getLocalName();
        if (util.checkIfIndividualNameExists(agentName)){
            Person person = util.getPersonData(agentName);
            sendSuccess(person);
        } else {
            sendFailure();
        }
    }
}
