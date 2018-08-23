package WebApplication.Model.Requests;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetMessagesRequest {

    private long userId;
    private long friendId;

    public long getFriendId() {
        return friendId;
    }

    public void setFriendId(long friendId) {
        this.friendId = friendId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public GetMessagesRequest(long userId, long friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }
}
