package WebApplication.Model.Responses;


import WebApplication.Model.Helpers.UserInfo;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetMyApplicantsResponse {

    private List<UserInfo> users;

    public List<UserInfo> getUsers() {
        return users;
    }

    public void setUsers(List<UserInfo> users) {
        this.users = users;
    }

    public GetMyApplicantsResponse(List<UserInfo> users) {
        this.users = users;
    }
}
