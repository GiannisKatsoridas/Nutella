package WebApplication.Model.Responses;


import WebApplication.Model.Entities.UsersEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetConnectionsResponse {

    public List<UsersEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UsersEntity> users) {
        this.users = users;
    }

    public GetConnectionsResponse(List<UsersEntity> users) {
        this.users = users;
    }

    private List<UsersEntity> users;
}
