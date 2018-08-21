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

    @POST
    @Path("job/insert")
    public InsertJobResponse InsertJob(InsertJobRequest request){

        return service.InsertJob(request);

    }

    @POST
    @Path("post/like")
    public LikeResponse Like(LikeRequest request){

        return service.Like(request);

    }

    @POST
    @Path("post/comment")
    public CommentResponse Comment(CommentRequest request){

        return service.Comment(request);

    }

    @GET
    @Path("post/getposts/{userId}")
    public GetPostsResponse GetPosts(@PathParam("userId") final long userId){

        GetPostsRequest request = new GetPostsRequest(userId);

        return service.GetPosts(request);

    }

    @GET
    @Path("user/search/{query}")
    public SearchResponse Search(@PathParam("query") final String query){

        SearchRequest request = new SearchRequest(query);

        return service.Search(request);
    }

    @GET
    @Path("job/getjobs/{userId}")
    public GetJobsResponse GetJobs(@PathParam("userId") final long userId){

        GetJobsRequest request = new GetJobsRequest(userId);

        return service.GetJobs(request);
    }

    @POST
    @Path("job/apply")
    public JobApplicationResponse JobApplication(JobApplicationRequest request){

        return service.JobApplication(request);

    }

    @GET
    @Path("job/getapplicants/{jobId}")
    public GetMyApplicantsResponse GetMyApplicants(@PathParam("jobId") final long jobId){

        GetMyApplicantsRequest request = new GetMyApplicantsRequest(jobId);

        return service.GetMyApplicants(request);
    }

    @GET
    @Path("job/getmyjobs/{userId}")
    public GetMyJobsResponse GetMyJobs(@PathParam("userId") final long userId){

        GetMyJobsRequest request = new GetMyJobsRequest(userId);

        return service.GetMyJobs(request);
    }

    @POST
    @Path("job/edit")
    public EditJobResponse EditJob(EditJobRequest request){

        return service.EditJob(request);

    }

    @POST
    @Path("connection/sendrequest")
    public SendConnectionRequestResponse SendConnectionRequest(SendConnectionRequestRequest request){

        return service.SendConnectionRequest(request);

    }

}
