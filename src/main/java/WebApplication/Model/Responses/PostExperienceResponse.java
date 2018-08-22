package WebApplication.Model.Responses;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PostExperienceResponse {

    ///If experienceId == -1 then there was an internal server error

    private long experienceId;

    public long getExperienceId() {
        return experienceId;
    }

    public void setExperienceId(long experienceId) {
        this.experienceId = experienceId;
    }

    public PostExperienceResponse(long experienceId) {
        this.experienceId = experienceId;
    }
}
