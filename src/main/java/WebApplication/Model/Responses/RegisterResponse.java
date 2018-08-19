package WebApplication.Model.Responses;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RegisterResponse {

    ///If userID == 0 then  there is already a user with this specific email address
    ///If userID == -1 then the confirmation password is not the same with the initial password
    ///If userID == -2 then there was another general server error

    public long userId;

    public RegisterResponse(long id){

        userId = id;

    }
}
