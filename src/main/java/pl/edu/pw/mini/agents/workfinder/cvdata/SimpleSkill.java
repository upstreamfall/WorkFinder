package pl.edu.pw.mini.agents.workfinder.cvdata;

import pl.edu.pw.mini.agents.workfinder.cvdata.Level;

/**
 * Created by pawel.bielicki on 2015-04-15.
 */
public class SimpleSkill {
    private String frameworkName;   //angular.js
    private Level level;            //guru

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getFrameworkName() {
        return frameworkName;
    }

    public void setFrameworkName(String frameworkName) {
        this.frameworkName = frameworkName;
    }
}

