package pl.edu.pw.mini.agents.workfinder.cvdata;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by pawel.bielicki on 2015-04-13.
 */
public class ProgrammerSkill {

    private String specialization;
    private int experienceYears;
    private Map<String, Level> frameworks;
    private Map<String, Level> languages;

    public ProgrammerSkill() {
        frameworks = new HashMap<>();
        languages = new HashMap<>();
    }

    public ProgrammerSkill(Properties prop) {
        specialization = prop.getProperty("specialization");
        experienceYears = Integer.parseInt(prop.getProperty("experienceYears"));
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

    public void addSkill(String framework, Level level){
        frameworks.put(framework, level);
    }

    public void removeSkill(String framework){
        frameworks.remove(framework);
    }

    public void addLanguage(String language, Level level){
        languages.put(language, level);
    }

    public void removeLanguage(String language){
        languages.remove(language);
    }
}
