package pl.edu.pw.mini.agents.workfinder;

/**
 * Created by pawel.bielicki on 2015-04-15.
 */
public class SimpleSkill {
    private String category;        //databases
    private Level level;            // guru
    private String[] frameworks;    //"Oracle", "PostgresSQL", "MongoDB"

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String[] getFrameworks() {
        return frameworks;
    }

    public void setFrameworks(String[] frameworks) {
        this.frameworks = frameworks;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}

