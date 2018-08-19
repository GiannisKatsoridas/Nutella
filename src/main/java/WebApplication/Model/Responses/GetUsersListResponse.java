package WebApplication.Model.Responses;


import WebApplication.Model.Entities.UsersEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetUsersListResponse {

    private List<UsersEntity> usersList;

    public GetUsersListResponse(List<UsersEntity> usersList) {
        this.usersList = usersList;
    }

    public List<UsersEntity> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<UsersEntity> usersList) {
        this.usersList = usersList;
    }
}
