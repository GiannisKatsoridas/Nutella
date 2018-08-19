package WebApplication.Implementation;

import WebApplication.Implementation.Database.DbQueries;
import WebApplication.Interface.*;
import WebApplication.Model.Requests.*;
import WebApplication.Model.Responses.*;

public class WebApplicationServiceImplementation implements WebApplicationServiceInterface {

    private DbQueries db;

    public WebApplicationServiceImplementation() {

        db = new DbQueries();

    }

    public RegisterResponse Register(RegisterRequest request) {

        if(!request.getPassword().equals(request.getPassConfirm())){
            return new RegisterResponse(-1);
        }

        return db.CreateUser(request);
    }

    public LoginResponse Login(LoginRequest request){

        return db.Login(request);

    }

    public GetUsersListResponse GetUsersList(){

        return db.GetUsersList();

    }

    public GetInfoResponse GetInfo(GetInfoRequest request) {

        return db.GetInfo(request);

    }
}
