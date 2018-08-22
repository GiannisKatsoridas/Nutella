package WebApplication.Model.Responses;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UpdateEducationResponse {

    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public UpdateEducationResponse(boolean success) {
        this.success = success;
    }
}
