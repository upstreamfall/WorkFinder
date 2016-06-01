package workfinder.utils.dto;

import jade.core.AID;

/**
 * Created by pbielicki on 01.06.2016.
 */
public class GlobalParameters {
    private static GlobalParameters _globalParameters = new GlobalParameters();
    private GlobalParameters() {
        ontologyProxyAgency = new AID("OntologyMaster", AID.ISLOCALNAME);
    }

    public static GlobalParameters getInstance() {
        return _globalParameters;
    }

    public AID ontologyProxyAgency;
}
