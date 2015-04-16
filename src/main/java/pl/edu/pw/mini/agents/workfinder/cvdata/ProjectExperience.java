package pl.edu.pw.mini.agents.workfinder.cvdata;

import pl.edu.pw.mini.agents.workfinder.cvdata.SimpleSkill;

import java.util.List;

/**
 * Created by pawel.bielicki on 2015-04-16.
 */
public class ProjectExperience {
    private String name;
    private String description;
    private List<SimpleSkill> usedFrameworks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SimpleSkill> getUsedFrameworks() {
        return usedFrameworks;
    }

    public void setUsedFrameworks(List<SimpleSkill> usedFrameworks) {
        this.usedFrameworks = usedFrameworks;
    }
}
