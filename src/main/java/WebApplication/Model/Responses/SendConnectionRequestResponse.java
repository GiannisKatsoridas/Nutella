package WebApplication.Model.Responses;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SendConnectionRequestResponse {

    private long notificationId;

    public long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(long notificationId) {
        this.notificationId = notificationId;
    }

    public SendConnectionRequestResponse(long notificationId) {
        this.notificationId = notificationId;
    }
}
