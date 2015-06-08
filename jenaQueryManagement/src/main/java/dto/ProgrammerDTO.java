package dto;

/**
 * Created by Pawel on 2015-06-08.
 */
public class ProgrammerDTO {
    private String name;
    private String email;

    public ProgrammerDTO(String name, String email){
        this.name = name;
        this.email = email;
    }

    public ProgrammerDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
