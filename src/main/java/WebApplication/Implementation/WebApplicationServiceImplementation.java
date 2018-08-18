package WebApplication.Implementation;

import WebApplication.Implementation.Database.DbQueries;
import WebApplication.Interface.*;
import WebApplication.Model.Requests.*;
import WebApplication.Model.Responses.*;

public class WebApplicationServiceImplementation implements WebApplicationServiceInterface {

    public RegisterResponse CreateUser(RegisterRequest request) {

        if(!request.getPassword().equals(request.getPassConfirm())){
            return new RegisterResponse(-1);
        }

        DbQueries db = new DbQueries();
        return db.CreateUser(request);
    }
}
