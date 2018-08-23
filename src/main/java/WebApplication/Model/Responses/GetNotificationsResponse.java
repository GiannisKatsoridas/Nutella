package WebApplication.Model.Responses;


import WebApplication.Model.Entities.NotificationsEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetNotificationsResponse {

    private List<NotificationsEntity> notifications;

    public List<NotificationsEntity> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotificationsEntity> notifications) {
        this.notifications = notifications;
    }

    public GetNotificationsResponse(List<NotificationsEntity> notifications) {
        this.notifications = notifications;
    }
}
