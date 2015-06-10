package dto;

/**
 * Created by pbielicki on 2015-06-08.
 */
public class SkillDTO {
    private String name;
    private int level;
    private int priority = -1;

    public SkillDTO() {
    }

    public SkillDTO(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public SkillDTO(String name, int level, int priority) {
        this(name, level);
        this.priority = priority;
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

    @Override
    public String toString() {
        return "SkillDTO{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", priority=" + priority +
                '}';
    }
}
