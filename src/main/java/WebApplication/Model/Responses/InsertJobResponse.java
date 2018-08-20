package WebApplication.Model.Responses;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class InsertJobResponse {

    ///If jobId == -1 then there was an internal server error

    private long jobId;

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public InsertJobResponse(long jobId) {
        this.jobId = jobId;
    }
}
