package WebApplication.Model.Requests;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetConversationsRequest {

    private long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public GetConversationsRequest(long userId) {
        this.userId = userId;
    }
}
