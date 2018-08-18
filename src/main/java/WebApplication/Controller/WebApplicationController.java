package WebApplication.Controller;


import WebApplication.Implementation.WebApplicationServiceImplementation;
import WebApplication.Model.Requests.*;
import WebApplication.Model.Responses.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Consumes({"application/json"})
@Produces({"application/json"})
public class WebApplicationController {

    private WebApplicationServiceImplementation service;

    public WebApplicationController(){

        service = new WebApplicationServiceImplementation();

    }

    @POST
    @Path("/user/register")
    public RegisterResponse CreateUser(RegisterRequest request){

        return service.CreateUser(request);

    }


}
