package WebApplication.Controller;

import WebApplication.Implementation.WebApplicationServiceImplementation;
import WebApplication.Model.Helpers.MyXMLObject;
import WebApplication.Model.Requests.*;
import WebApplication.Model.Responses.*;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;

public class WebApplicationController {

    private WebApplicationServiceImplementation service;

    public WebApplicationController(){

        service = new WebApplicationServiceImplementation();

    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("/user/register")
    public RegisterResponse CreateUser(RegisterRequest request){

        return service.Register(request);

    }

    @GET
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("user/login/{email}/{password}")
    public LoginResponse Login(@PathParam("email") final String email, @PathParam("password") final String password){

        LoginRequest request = new LoginRequest(email, password);

        return service.Login(request);

    }

    @GET
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("user/getusers")
    public GetUsersListResponse GetUsersList(){

        return service.GetUsersList();

    }

    @GET
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("user/getinfo/{id}")
    public GetInfoResponse GetInfo(@PathParam("id") final long id){

        GetInfoRequest request = new GetInfoRequest(id);

        return service.GetInfo(request);

    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("post/insert")
    public InsertPostResponse InsertPost(InsertPostRequest request){

        return service.InsertPost(request);

    }

    @GET
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("user/getconnections/{userid}")
    public GetConnectionsResponse GetConnections(@PathParam("userid") final long userId){

        GetConnectionsRequest request = new GetConnectionsRequest(userId);

        GetConnectionsResponse resp = service.GetConnections(request);

        if(resp.getUsers() == null){
            resp = null;
        }

        return resp;
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("job/insert")
    public InsertJobResponse InsertJob(InsertJobRequest request){

        return service.InsertJob(request);

    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("post/like")
    public LikeResponse Like(LikeRequest request){

        return service.Like(request);

    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("post/comment")
    public CommentResponse Comment(CommentRequest request){

        return service.Comment(request);

    }

    @GET
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("post/getposts/{userId}")
    public GetPostsResponse GetPosts(@PathParam("userId") final long userId){

        GetPostsRequest request = new GetPostsRequest(userId);

        return service.GetPosts(request);

    }

    @GET
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("user/search/{query}")
    public SearchResponse Search(@PathParam("query") final String query){

        SearchRequest request = new SearchRequest(query);

        return service.Search(request);
    }

    @GET
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("job/getjobs/{userId}")
    public GetJobsResponse GetJobs(@PathParam("userId") final long userId){

        GetJobsRequest request = new GetJobsRequest(userId);

        return service.GetJobs(request);
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("job/apply")
    public JobApplicationResponse JobApplication(JobApplicationRequest request){

        return service.JobApplication(request);

    }

    @GET
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("job/getapplicants/{jobId}")
    public GetMyApplicantsResponse GetMyApplicants(@PathParam("jobId") final long jobId){

        GetMyApplicantsRequest request = new GetMyApplicantsRequest(jobId);

        return service.GetMyApplicants(request);
    }

    @GET
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("job/getmyjobs/{userId}")
    public GetMyJobsResponse GetMyJobs(@PathParam("userId") final long userId){

        GetMyJobsRequest request = new GetMyJobsRequest(userId);

        return service.GetMyJobs(request);
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("job/edit")
    public EditJobResponse EditJob(EditJobRequest request){

        return service.EditJob(request);

    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("connection/sendrequest")
    public SendConnectionRequestResponse SendConnectionRequest(SendConnectionRequestRequest request){

        return service.SendConnectionRequest(request);

    }

    @GET
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("connection/getrequests/{userId}")
    public GetConnectionRequestsResponse GetConnectionRequests(@PathParam("userId") final long userId){

        GetConnectionRequestsRequest request = new GetConnectionRequestsRequest(userId);

        return service.GetConnectionRequests(request);
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("connection/accept")
    public AcceptConnectionResponse AcceptConnection(AcceptConnectionRequest request){

        return service.AcceptConnection(request);

    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("connection/reject")
    public RejectConnectionResponse RejectConnection(RejectConnectionRequest request){

        return service.RejectConnection(request);

    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("experience/post")
    public PostExperienceResponse PostExperience(PostExperienceRequest request){

        return service.PostExperience(request);

    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("education/post")
    public PostEducationResponse PostEducation(PostEducationRequest request){

        return service.PostEducation(request);

    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("skill/post")
    public PostSkillResponse PostSkill(PostSkillRequest request){

        return service.PostSkill(request);

    }

    @GET
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("user/getpersonalinfo/{userId}")
    public GetPersonalInfoResponse GetPersonalInfo(@PathParam("userId") final long userId){

        GetPersonalInfoRequest request = new GetPersonalInfoRequest(userId);

        return service.GetPersonalInfo(request);
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("experience/update")
    public UpdateExperienceResponse UpdateExperience(UpdateExperienceRequest request){

        return service.UpdateExperience(request);

    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("education/update")
    public UpdateEducationResponse UpdateEducation(UpdateEducationRequest request){

        return service.UpdateEducation(request);

    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("skill/update")
    public UpdateSkillResponse UpdateSkill(UpdateSkillRequest request){

        return service.UpdateSkill(request);

    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("message/send")
    public SendMessageResponse SendMessage(SendMessageRequest request){

        return service.SendMessage(request);

    }

    @GET
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("message/get/{userId}/{friendId}")
    public GetMessagesResponse GetMessages(@PathParam("userId") final long userId, @PathParam("friendId") final long friendId){

        GetMessagesRequest request = new GetMessagesRequest(userId, friendId);

        return service.GetMessages(request);

    }

    @GET
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("notifications/get/{userId}")
    public GetNotificationsResponse GetNotifications(@PathParam("userId") final long userId){

        GetNotificationsRequest request = new GetNotificationsRequest(userId);

        return service.GetNotifications(request);
    }

    @GET
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("user/getconversations/{userId}")
    public GetConversationsResponse GetConversations(@PathParam("userId") final long userId){

        GetConversationsRequest request = new GetConversationsRequest(userId);

        return service.GetConversations(request);
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("user/email/update")
    public UpdateEmailResponse UpdateEmail(UpdateEmailRequest request){

        return service.UpdateEmail(request);

    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("user/password/update")
    public UpdatePasswordResponse UpdatePassword(UpdatePasswordRequest request){

        return service.UpdatePassword(request);

    }

    @GET
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("job/getmyapplications/{userId}")
    public GetMyJobApplicationsResponse GetMyApplications(@PathParam("userId") final long userId){

        GetMyApplicationsRequest request = new GetMyApplicationsRequest(userId);

        GetMyJobApplicationsResponse resp = service.GetMyApplications(request);

        return resp;
    }

    @GET
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("post/get/{postId}")
    public GetPostResponse GetPost(@PathParam("postId") final long postId){

        GetPostRequest request = new GetPostRequest(postId);

        return service.GetPost(request);
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces({MediaType.APPLICATION_JSON})
    @Path("image/upload/{userId}")
    public UploadFileResponse UploadImage(@PathParam("userId") final long userId/*, @FormDataParam("file") InputStream uploadedInputStream, @FormDataParam("file") FormDataContentDisposition fileDetail*/){

        return service.UploadImage(/*uploadedInputStream,*/ userId);

    }

    @GET
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("user/export/{userId}")
    public ExportUserResponse ExportUser(@PathParam("userId") final long userId){

        ExportUserRequest request = new ExportUserRequest(userId);

        MyXMLObject pojo = service.ExportUser(request);

        String xml = null;
        try {

            JAXBContext context = JAXBContext.newInstance(MyXMLObject.class);
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

            StringWriter sw = new StringWriter();
            m.marshal(pojo, sw);
            xml = sw.toString();

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return new ExportUserResponse(xml);
    }

    @GET
    @Path("image/get/{userId}")
    @Produces("image/jpg")
    public Response GetImage(@PathParam("userId") final long userId){

        byte[] bytes = service.GetImage(userId).getData();

        return Response.ok(bytes).build();
    }
}
