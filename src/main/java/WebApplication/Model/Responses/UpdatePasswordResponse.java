package WebApplication.Model.Responses;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UpdatePasswordResponse {

    ///If userId == 0 then the password given does not match the user's password
    ///If userId == -1 then there was an internal server error

    private long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public UpdatePasswordResponse(long userId) {
        this.userId = userId;
    }
}
