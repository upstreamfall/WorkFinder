package pl.edu.pw.mini.agents.workfinder.webui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class AgentController {

    private final AgentService agentService;

    @Autowired
    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }


    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value="agent/{agentName}", method=RequestMethod.POST)
    public String pathVars(@PathVariable String agentName) {
        agentService.sendMessage(agentName);
        return "OK";
    }
}