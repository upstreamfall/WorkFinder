package pl.edu.pw.mini.agents.workfinder.cvdata;

import pl.edu.pw.mini.agents.workfinder.cvdata.SimpleSkill;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pawel.bielicki on 2015-04-13.
 */

//TODO
//Create interface to synchronize frameworks list with projects.
public class ProgrammerSkill {

    private String specialization;
    private int experienceYears;
    private List<SimpleSkill> frameworks;
    private List<ProjectExperience> projects;

    public ProgrammerSkill() {
        frameworks = new ArrayList<>();
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

    public List<SimpleSkill> getFrameworks() {
        return frameworks;
    }

    public void setFrameworks(List<SimpleSkill> frameworks) {
        this.frameworks = frameworks;
    }

    public List<ProjectExperience> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectExperience> projects) {
        this.projects = projects;
    }
}
