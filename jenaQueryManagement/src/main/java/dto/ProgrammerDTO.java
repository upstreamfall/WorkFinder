package dto;

import java.util.List;

/**
 * Created by Pawel on 2015-06-08.
 */
public class ProgrammerDTO extends WorkerDTO {
    private String email;

    public ProgrammerDTO() {
        super();
    }

    public ProgrammerDTO(String name, String email, List<SkillDTO> skillDTOList) {
        super(name, skillDTOList);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ProgrammerDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
