package pl.edu.pw.mini.agents.workfinder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pawel.bielicki on 2015-04-13.
 */
public class ProgrammerSkills {

    private String specialization;
    private int experienceYears;

    private List<SimpleSkill> skills;

    public ProgrammerSkills() {
        skills = new ArrayList<>();
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

    public void addSkill(SimpleSkill skill){
        skills.add(skill);
    }

    public void removeSkill(SimpleSkill skill){
        skills.remove(skill);
    }
}
