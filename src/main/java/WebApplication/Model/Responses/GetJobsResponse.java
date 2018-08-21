package WebApplication.Model.Responses;


import WebApplication.Model.Entities.JobsEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetJobsResponse {

    private List<JobsEntity> results;

    public List<JobsEntity> getResults() {
        return results;
    }

    public void setResults(List<JobsEntity> results) {
        this.results = results;
    }

    public GetJobsResponse(List<JobsEntity> results) {
        this.results = results;
    }
}
