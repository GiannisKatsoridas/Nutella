package WebApplication.Implementation.Database;

import WebApplication.Model.Entities.UsersEntity;
import WebApplication.Model.Requests.*;

public class DbQueriesHelper {

    public static UsersEntity createUsersEntity(UsersEntity user, RegisterRequest request){

        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setIsAdmin(false);
        user.setPhone(request.getPhone());
        user.setPassword(request.getPassword());

        return user;
    }

}
