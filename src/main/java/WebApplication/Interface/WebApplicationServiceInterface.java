package WebApplication.Interface;

import WebApplication.Model.Requests.*;
import WebApplication.Model.Responses.*;

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
}
