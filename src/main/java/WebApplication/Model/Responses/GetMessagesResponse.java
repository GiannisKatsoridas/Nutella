package WebApplication.Model.Responses;


import WebApplication.Model.Entities.MessagesEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetMessagesResponse {

    private List<MessagesEntity> messages;

    public List<MessagesEntity> getMessages() {
        return messages;
    }

    public void setMessages(List<MessagesEntity> messages) {
        this.messages = messages;
    }

    public GetMessagesResponse(List<MessagesEntity> messages) {
        this.messages = messages;
    }
}
