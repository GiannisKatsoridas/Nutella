package WebApplication.Model.Responses;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UpdateExperienceResponse {

    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public UpdateExperienceResponse(boolean success) {
        this.success = success;
    }
}
