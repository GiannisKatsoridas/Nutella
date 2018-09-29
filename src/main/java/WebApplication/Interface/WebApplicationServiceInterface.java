package WebApplication.Interface;

import WebApplication.Model.Requests.*;
import WebApplication.Model.Responses.*;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public interface WebApplicationServiceInterface {

    RegisterResponse Register(RegisterRequest request);

    LoginResponse Login(LoginRequest request);

    GetUsersListResponse GetUsersList();

    GetInfoResponse GetInfo(GetInfoRequest request);

    InsertPostResponse InsertPost(InsertPostRequest request);

    GetConnectionsResponse GetConnections(GetConnectionsRequest request);

    InsertJobResponse InsertJob(InsertJobRequest request);

    LikeResponse Like(LikeRequest request);

    CommentResponse Comment(CommentRequest request);

    GetPostsResponse GetPosts(GetPostsRequest request);

    SearchResponse Search(SearchRequest request);

    GetJobsResponse GetJobs(GetJobsRequest request);

    JobApplicationResponse JobApplication(JobApplicationRequest request);

    GetMyApplicantsResponse GetMyApplicants(GetMyApplicantsRequest request);

    GetMyJobsResponse GetMyJobs(GetMyJobsRequest request);

    EditJobResponse EditJob(EditJobRequest request);

    SendConnectionRequestResponse SendConnectionRequest(SendConnectionRequestRequest request);

    GetConnectionRequestsResponse GetConnectionRequests(GetConnectionRequestsRequest request);

    AcceptConnectionResponse AcceptConnection(AcceptConnectionRequest request);

    RejectConnectionResponse RejectConnection(RejectConnectionRequest request);

    PostExperienceResponse PostExperience(PostExperienceRequest request);

    PostEducationResponse PostEducation(PostEducationRequest request);

    PostSkillResponse PostSkill(PostSkillRequest request);

    GetPersonalInfoResponse GetPersonalInfo(GetConnectionRequestsRequest request);

    UpdateExperienceResponse UpdateExperience(UpdateExperienceRequest request);

    UpdateEducationResponse UpdateEducation(UpdateEducationRequest request);

    UpdateSkillResponse UpdateSkill(UpdateSkillRequest request);

    SendMessageResponse SendMessage(SendMessageRequest request);

    GetMessagesResponse GetMessages(GetMessagesRequest request);

    GetNotificationsResponse GetNotifications(GetNotificationsRequest request);

    GetConversationsResponse GetConversations(GetConversationsRequest request);

    UpdateEmailResponse UpdateEmail(UpdateEmailRequest request);

    UpdatePasswordResponse UpdatePassword(UpdatePasswordRequest request);

    GetMyJobApplicationsResponse GetMyApplications(GetMyApplicationsRequest request);

    GetPostResponse GetPost(GetPostRequest request);

    UploadFileResponse UploadImage(InputStream request, long userId);
}
