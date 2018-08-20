package WebApplication.Controller;


import WebApplication.Implementation.WebApplicationServiceImplementation;
import WebApplication.Model.Requests.*;
import WebApplication.Model.Responses.*;

import javax.ws.rs.*;

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

        return service.Register(request);

    }

    @GET
    @Path("user/login/{email}/{password}")
    public LoginResponse Login(@PathParam("email") final String email, @PathParam("password") final String password){

        LoginRequest request = new LoginRequest(email, password);

        return service.Login(request);

    }

    @GET
    @Path("user/getusers")
    public GetUsersListResponse GetUsersList(){

        return service.GetUsersList();

    }

    @GET
    @Path("user/getinfo/{id}")
    public GetInfoResponse GetInfo(@PathParam("id") final long id){

        GetInfoRequest request = new GetInfoRequest(id);

        return service.GetInfo(request);

    }

    @POST
    @Path("post/insert")
    public InsertPostResponse InsertPost(InsertPostRequest request){

        return service.InsertPost(request);

    }

    @GET
    @Path("user/getconnections/{userid}")
    public GetConnectionsResponse GetConnections(@PathParam("userid") final long userId){

        GetConnectionsRequest request = new GetConnectionsRequest(userId);

        return service.GetConnections(request);

    }

}
