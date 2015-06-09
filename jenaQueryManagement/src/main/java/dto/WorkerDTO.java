package dto;

import java.util.List;

/**
 * Created by pbielicki on 2015-06-09.
 */
public abstract class WorkerDTO {
    protected String name;
    protected List<SkillDTO> skillList;

    public WorkerDTO(String name, List<SkillDTO> skillList) {
        this.name = name;
        this.skillList = skillList;
    }

    public WorkerDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SkillDTO> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<SkillDTO> skillList) {
        this.skillList = skillList;
    }
}
