package WebApplication.Model.Responses;


import WebApplication.Model.Entities.JobsEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetJobsResponse {

    private List<JobsEntity> fromFriends;
    private List<JobsEntity> alike;
    private List<JobsEntity> fromNeighbors;

    public List<JobsEntity> getFromNeighbors() {
        return fromNeighbors;
    }

    public void setFromNeighbors(List<JobsEntity> fromNeighbors) {
        this.fromNeighbors = fromNeighbors;
    }

    public GetJobsResponse(List<JobsEntity> fromFriends, List<JobsEntity> alike, List<JobsEntity> fromNeighbors) {
        this.fromFriends = fromFriends;
        this.alike = alike;
        this.fromNeighbors = fromNeighbors;
    }

    public List<JobsEntity> getAlike() {
        return alike;
    }

    public void setAlike(List<JobsEntity> alike) {
        this.alike = alike;
    }


    public List<JobsEntity> getFromFriends() {
        return fromFriends;
    }

    public void setFromFriends(List<JobsEntity> fromFriends) {
        this.fromFriends = fromFriends;
    }
}
