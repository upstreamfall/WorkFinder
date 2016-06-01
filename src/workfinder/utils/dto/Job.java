package workfinder.utils.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pbielicki on 31.05.2016.
 */
public class Job implements Serializable{
    private List<Skill> skills;

    public Job(List<Skill> skills) {
        this.skills = skills;
    }
}
