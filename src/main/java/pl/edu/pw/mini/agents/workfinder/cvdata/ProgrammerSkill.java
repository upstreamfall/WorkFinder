package pl.edu.pw.mini.agents.workfinder.cvdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pawel.bielicki on 2015-04-13.
 */

//TODO
//How synchronize frameworks list with projects?
public class ProgrammerSkill {

    private String specialization;
    private int experienceYears;
    private Map<String, Level> frameworks;
    private List<ProjectExperience> projects;

    public ProgrammerSkill() {
        frameworks = new HashMap<>();
        projects = new ArrayList<>();
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public List<ProjectExperience> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectExperience> projects) {
        this.projects = projects;
    }

    public void addSkill(String framework, Level level){
        frameworks.put(framework, level);
    }

    public void removeSkill(String framework){
        frameworks.remove(framework);
    }
}
