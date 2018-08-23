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

    @GET
    @Path("connection/getrequests/{userId}")
    public GetConnectionRequestsResponse GetConnectionRequests(@PathParam("userId") final long userId){

        GetConnectionRequestsRequest request = new GetConnectionRequestsRequest(userId);

        return service.GetConnectionRequests(request);
    }

    @POST
    @Path("connection/accept")
    public AcceptConnectionResponse AcceptConnection(AcceptConnectionRequest request){

        return service.AcceptConnection(request);

    }

    @POST
    @Path("connection/reject")
    public RejectConnectionResponse RejectConnection(RejectConnectionRequest request){

        return service.RejectConnection(request);

    }

    @POST
    @Path("experience/post")
    public PostExperienceResponse PostExperience(PostExperienceRequest request){

        return service.PostExperience(request);

    }

    @POST
    @Path("education/post")
    public PostEducationResponse PostEducation(PostEducationRequest request){

        return service.PostEducation(request);

    }

    @POST
    @Path("skill/post")
    public PostSkillResponse PostSkill(PostSkillRequest request){

        return service.PostSkill(request);

    }

    @GET
    @Path("user/getpersonalinfo/{userId}")
    public GetPersonalInfoResponse GetPersonalInfo(@PathParam("userId") final long userId){

        GetConnectionRequestsRequest request = new GetConnectionRequestsRequest(userId);

        return service.GetPersonalInfo(request);
    }

    @POST
    @Path("experience/update")
    public UpdateExperienceResponse UpdateExperience(UpdateExperienceRequest request){

        return service.UpdateExperience(request);

    }

    @POST
    @Path("education/update")
    public UpdateEducationResponse UpdateEducation(UpdateEducationRequest request){

        return service.UpdateEducation(request);

    }

    @POST
    @Path("skill/update")
    public UpdateSkillResponse UpdateSkill(UpdateSkillRequest request){

        return service.UpdateSkill(request);

    }

    @POST
    @Path("message/send")
    public SendMessageResponse SendMessage(SendMessageRequest request){

        return service.SendMessage(request);

    }

    @GET
    @Path("message/get/{userId}/{friendId}")
    public GetMessagesResponse GetMessages(@PathParam("userId") final long userId, @PathParam("friendId") final long friendId){

        GetMessagesRequest request = new GetMessagesRequest(userId, friendId);

        return service.GetMessages(request);

    }

    @GET
    @Path("notifications/get/{userId}")
    public GetNotificationsResponse GetNotifications(@PathParam("userId") final long userId){

        GetNotificationsRequest request = new GetNotificationsRequest(userId);

        return service.GetNotifications(request);
    }

    @GET
    @Path("user/getconversations/{userId}")
    public GetConversationsResponse GetConversations(@PathParam("userId") final long userId){

        GetConversationsRequest request = new GetConversationsRequest(userId);

        return service.GetConversations(request);
    }

    @POST
    @Path("user/email/update")
    public UpdateEmailResponse UpdateEmail(UpdateEmailRequest request){

        return service.UpdateEmail(request);

    }

    @POST
    @Path("user/password/update")
    public UpdatePasswordResponse UpdatePassword(UpdatePasswordRequest request){

        return service.UpdatePassword(request);

    }
}
