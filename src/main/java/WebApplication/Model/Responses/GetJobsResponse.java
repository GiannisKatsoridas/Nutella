package WebApplication.Model.Responses;


import WebApplication.Model.Entities.JobsEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetJobsResponse {

    private List<JobsEntity> fromFriends;

    public GetJobsResponse(List<JobsEntity> fromFriends, List<JobsEntity> alike) {
        this.fromFriends = fromFriends;
        this.alike = alike;
    }

    public List<JobsEntity> getAlike() {
        return alike;
    }

    public void setAlike(List<JobsEntity> alike) {
        this.alike = alike;
    }

    private List<JobsEntity> alike;

    public List<JobsEntity> getFromFriends() {
        return fromFriends;
    }

    public void setFromFriends(List<JobsEntity> fromFriends) {
        this.fromFriends = fromFriends;
    }
}
