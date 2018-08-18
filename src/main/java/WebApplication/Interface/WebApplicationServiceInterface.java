package WebApplication.Interface;

import WebApplication.Model.Requests.*;
import WebApplication.Model.Responses.*;

public interface WebApplicationServiceInterface {

    public RegisterResponse CreateUser(RegisterRequest request);

}
