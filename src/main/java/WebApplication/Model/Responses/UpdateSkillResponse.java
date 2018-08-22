package WebApplication.Model.Responses;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UpdateSkillResponse {

    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public UpdateSkillResponse(boolean success) {
        this.success = success;
    }
}
