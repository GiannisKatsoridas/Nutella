package WebApplication.Model.Requests;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SendMessageRequest {

    private long userFrom;
    private long userTo;
    private String message;

    public long getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(long userFrom) {
        this.userFrom = userFrom;
    }

    public long getUserTo() {
        return userTo;
    }

    public void setUserTo(long userTo) {
        this.userTo = userTo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
