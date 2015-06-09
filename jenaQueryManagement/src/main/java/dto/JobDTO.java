package dto;

/**
 * Created by pbielicki on 2015-06-09.
 */
public class JobDTO extends WorkerDTO {

    public JobDTO() {
        super();
    }

    @Override
    public String toString() {
        return "JobDTO{" +
                "name='" + name + '\'' +
                ", skillList=" + skillList +
                '}';
    }
}
