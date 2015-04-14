package pl.edu.pw.mini.agents.workfinder;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

/**
 * Created by pawel.bielicki on 2015-04-14.
 */
public class DFAgentManager {

    public DFAgentManager(){}

    public boolean register(Agent context, String serviceType, String serviceName){
        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.setName(context.getAID());

        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType(serviceType);
        serviceDescription.setName(serviceName);
        dfAgentDescription.addServices(serviceDescription);

        try {
            DFService.register(context, dfAgentDescription);
        } catch (FIPAException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public DFAgentDescription[] searchForService(Agent context, String serviceType){
        DFAgentDescription template = new DFAgentDescription();
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType(serviceType);

        template.addServices(serviceDescription);
        try {
            return DFService.search(context, template);
        } catch (FIPAException e) {
            e.printStackTrace();
        }

        return null;
    }
}
