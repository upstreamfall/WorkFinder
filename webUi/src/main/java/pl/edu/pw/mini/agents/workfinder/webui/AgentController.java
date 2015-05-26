package pl.edu.pw.mini.agents.workfinder.webui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AgentController {

    private final AgentService agentService;
    private final Map<String, List<String>> resonses = new HashMap<>();

    @Autowired
    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }


    @RequestMapping(value="agent/{agentName}", method=RequestMethod.GET)
    public List<String> agentsMessages(@PathVariable String agentName) {
        return resonses.get(agentName);
    }

    @RequestMapping(value="agent/{agentName}", method=RequestMethod.POST)
    public void sendMessage(@PathVariable String agentName, @RequestBody String content) {
        agentService.sendMessage(agentName, content);
    }

    @RequestMapping(value="agent/{agentName}/response", method=RequestMethod.POST)
    public void reciveMessageFromAgent(@PathVariable String agentName, @RequestBody String content) {
        if (!resonses.containsKey(agentName)) {
            resonses.put(agentName, new ArrayList<String>());
        }
        resonses.get(agentName).add(content);
    }
}