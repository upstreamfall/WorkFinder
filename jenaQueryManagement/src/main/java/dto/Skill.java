package dto;

/**
 * Created by pbielicki on 2015-06-08.
 */
public class Skill {
    private String name;
    private int level;
    private int priority;

    public Skill(String name, int level, int priority) {
        this.name = name;
        this.level = level;
        this.priority = priority;
    }

    public Skill(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
