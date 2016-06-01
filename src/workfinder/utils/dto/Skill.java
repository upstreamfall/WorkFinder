package workfinder.utils.dto;

import java.io.Serializable;

/**
 * Created by pbielicki on 05.05.2016.
 */
public class Skill implements Serializable {
    private final String name;
    private int level;

    public Skill(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public void updateLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Skill: [name:" + name + ", level:" + level + "]";
    }
}
