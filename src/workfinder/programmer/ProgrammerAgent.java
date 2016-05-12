package workfinder.programmer;

import jade.core.AID;
import workfinder.utils.ExtendedAgent;

import static java.lang.Thread.sleep;

/**
 * Created by pbielicki on 04.05.2016.
 */
public class ProgrammerAgent extends ExtendedAgent {
    AID ontologyProxyAgency;

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
        ontologyProxyAgency = new AID("OntologyMaster", AID.ISLOCALNAME);
    }
}
