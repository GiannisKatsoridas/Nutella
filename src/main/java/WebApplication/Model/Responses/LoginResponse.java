package WebApplication.Model.Responses;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LoginResponse {

    ///If userId == 0 then the given credentials do not match any user in the database
    ///If userId == -1 then there was an internal server error

    @JsonProperty("userId")
    private long userId;
    @JsonProperty("isAdmin")
    private boolean isAdmin;

    public LoginResponse(long userId, boolean isAdmin) {
        this.userId = userId;
        this.isAdmin = isAdmin;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
