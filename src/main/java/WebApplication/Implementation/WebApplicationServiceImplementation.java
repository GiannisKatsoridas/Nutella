package WebApplication.Implementation;

import WebApplication.Implementation.Database.DbQueries;
import WebApplication.Implementation.Database.DbQueriesHelper;
import WebApplication.Implementation.Database.JPAResource;
import WebApplication.Implementation.Optimizations.JobsOptimizations;
import WebApplication.Implementation.Optimizations.PostsOptimizations;
import WebApplication.Interface.*;
import WebApplication.Model.Entities.*;
import WebApplication.Model.Helpers.Article;
import WebApplication.Model.Helpers.UserInfo;
import WebApplication.Model.Requests.*;
import WebApplication.Model.Responses.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class WebApplicationServiceImplementation implements WebApplicationServiceInterface {

    private DbQueries db;

    public WebApplicationServiceImplementation() {

        db = new DbQueries();

    }

    public RegisterResponse Register(RegisterRequest request) {

        if(!request.getPassword().equals(request.getPassConfirm())){
            return new RegisterResponse(-1);
        }

        long id;

        UsersEntity user = DbQueriesHelper.CreateUsersEntity(new UsersEntity(), request);

        id = db.InsertUser(user);

        if(id>0) {
            PicturesEntity picture = DbQueriesHelper.CreatePicturesEntity(new PicturesEntity(), id, request.getImage());
            id = db.InsertPicture(picture);
        }

        return new RegisterResponse(id);
    }

    public LoginResponse Login(LoginRequest request){

        UsersEntity user = db.FindUserByEmailPassword(request.getEmail(), request.getPassword());

        return new LoginResponse(user.getId(), user.getIsAdmin());

    }

    public GetUsersListResponse GetUsersList(){

        List<UsersEntity> users = db.GetUsersList();

        return new GetUsersListResponse(users);

    }

    public GetInfoResponse GetInfo(GetInfoRequest request) {

        UsersEntity user = db.GetUserById(request.getUserId());
        PicturesEntity picture = db.GetPicture(request.getUserId());

        return new GetInfoResponse(new UserInfo(user.getFirstName(), user.getLastName(), user.getEmail(), picture.getLink()));
    }

    public InsertPostResponse InsertPost(InsertPostRequest request){

        PostsEntity post = DbQueriesHelper.CreatePost(new PostsEntity(), request);

        EntityManager em = JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        long postId = db.InsertPost(em, post);

        List<String> media = request.getMedia();

        for(int i=0; i<media.size() && postId!=-1; i++){
            postId = db.InsertMedia(em, postId, media.get(i));
        }

        if(postId != -1){
            tx.commit();
        }
        else{
            tx.rollback();
        }

        PostsOptimizations.AddPost(postId);

        return new InsertPostResponse(postId);
    }

    public GetConnectionsResponse GetConnections(GetConnectionsRequest request){

        List<UserInfo> users = db.GetConnections(request.getUserId());

        return new GetConnectionsResponse(users);

    }

    public InsertJobResponse InsertJob(InsertJobRequest request) {

        JobsEntity job = DbQueriesHelper.CreateJob(new JobsEntity(), request);

        EntityManager em = JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        long jobId = DbQueriesHelper.GetLastJobId(em);
        job.setId(jobId);

        jobId = db.InsertJob(em, job);

        if(jobId == -1){
            tx.rollback();
        }
        else {
            boolean result = true;
            for (String j : request.getSkills()){
                JobrequirementsEntity jr = DbQueriesHelper.CreateJobRequirement(new JobrequirementsEntity(), j, jobId);
                result = db.InsertJobRequirement(em, jr);
                if(!result){
                    tx.rollback();
                    break;
                }
            }
            if(result){
                tx.commit();
            }
        }

        JobsOptimizations.AddJob(jobId);

        em.close();

        return new InsertJobResponse(jobId);
    }

    public LikeResponse Like(LikeRequest request) {

        LikesEntity like = DbQueriesHelper.CreateLike(new LikesEntity(), request);

        boolean result = db.Like(like);
        long id;

        if(result) {
            id = db.NotifyLike(request.getUserId(), request.getPostId());
        }
        else{
            id = -1;
        }

        PostsOptimizations.PostReact(request.getUserId(), request.getPostId());

        return new LikeResponse(id);
    }

    public CommentResponse Comment(CommentRequest request) {

        CommentsEntity like = DbQueriesHelper.CreateComment(new CommentsEntity(), request);

        boolean result = db.Comment(like);
        long id;

        if(result) {
            id = db.NotifyComment(request.getUserId(), request.getPostId());
        }
        else{
            id = -1;
        }

        PostsOptimizations.PostReact(request.getUserId(), request.getPostId());

        return new CommentResponse(id);
    }

    public GetPostsResponse GetPosts(GetPostsRequest request) {

        List<PostsEntity> postsFromFriends = db.GetPostsFromFriends(request.getUserId());
        List<PostsEntity> postsFromNeighbors = db.GetPostsFromNeighbors(request.getUserId());

        for(PostsEntity n: postsFromNeighbors){
            if(!postsFromFriends.contains(n)){
                postsFromFriends.add(n);
            }
        }

        List<Article> articles = new ArrayList<Article>();

        List<LikesEntity> likes;
        List<CommentsEntity> comments;

        for(int i=0; i<postsFromFriends.size(); i++){

            likes = db.GetLikes(postsFromFriends.get(i).getId());
            comments = db.GetComments(postsFromFriends.get(i).getId());
            articles.add(new Article(postsFromFriends.get(i).getText(), likes, comments));

        }

        return new GetPostsResponse(articles);
    }

    public SearchResponse Search(SearchRequest request) {

        List<UserInfo> results = db.Search(request.getQuery());

        return new SearchResponse(results);
    }

    public GetJobsResponse GetJobs(GetJobsRequest request) {

        List<JobsEntity> friends = db.GetJobsFromFriends(request.getUserId());
        List<JobsEntity> alike = db.GetJobsAlike(request.getUserId());
        List<JobsEntity> fromNeighbors = db.GetJobsFromClosestNeighbors(request.getUserId());

        ArrayList<JobsEntity> toDelete = new ArrayList<JobsEntity>();

        for(JobsEntity j: alike){
            if(friends.contains(j) || j.getId() == request.getUserId()){
                toDelete.add(j);
            }
        }

        for(JobsEntity j: toDelete){
            alike.remove(j);
        }

        toDelete = new ArrayList<JobsEntity>();

        for(JobsEntity j: fromNeighbors){
            if(friends.contains(j) || alike.contains(j) || j.getId() == request.getUserId()){
                toDelete.add(j);
            }
        }

        for(JobsEntity j: toDelete){
            fromNeighbors.remove(j);
        }

        return new GetJobsResponse(friends, alike, fromNeighbors);
    }

    public JobApplicationResponse JobApplication(JobApplicationRequest request) {

        JobapplicationsEntity ja = DbQueriesHelper.CreateJobApplication(new JobapplicationsEntity(), request.getUserId(), request.getJobId());

        boolean success = db.InsertJobApplication(ja);

        JobsOptimizations.JobApplication(request.getUserId(), request.getJobId());

        return new JobApplicationResponse(success);
    }

    public GetMyApplicantsResponse GetMyApplicants(GetMyApplicantsRequest request){

        List<UserInfo> users = db.GetJobApplicants(request.getJobId());

        return new GetMyApplicantsResponse(users);
    }

    public GetMyJobsResponse GetMyJobs(GetMyJobsRequest request) {

        List<JobsEntity> jobs = db.GetJobsByUser(request.getUserId());

        return new GetMyJobsResponse(jobs);
    }

    public EditJobResponse EditJob(EditJobRequest request) {

        boolean result = db.EditJob(request.getJobId(), request.getJobTitle(), request.getJobDescription());

        return new EditJobResponse(result);
    }

    public SendConnectionRequestResponse SendConnectionRequest(SendConnectionRequestRequest request) {

        FriendrequestEntity fr = DbQueriesHelper.CreateConnectionRequest(new FriendrequestEntity(), request.getSender(), request.getReceiver());

        boolean success = db.InsertConnectionRequest(fr);
        long id;

        if(success) {
            id = db.NotifyConnectionRequest(request.getSender(), request.getReceiver());
        }
        else{
            id = -1;
        }

        return new SendConnectionRequestResponse(id);
    }

    public GetConnectionRequestsResponse GetConnectionRequests(GetConnectionRequestsRequest request) {

        List<UsersEntity> connectionRequests = db.GetConnectionRequests(request.getUserId());

        List<UserInfo> result = new ArrayList<UserInfo>();
        PicturesEntity image;

        for(UsersEntity u: connectionRequests){

            image = db.GetPicture(u.getId());
            result.add(new UserInfo(u.getFirstName(), u.getLastName(), u.getEmail(), image.getLink()));

        }

        return new GetConnectionRequestsResponse(result);
    }

    public AcceptConnectionResponse AcceptConnection(AcceptConnectionRequest request){

        long id;

        boolean result = db.DeleteConnectionRequest(request.getUserFromId(), request.getUserToId());

        if(!result){
            return new AcceptConnectionResponse(-1);
        }

        result = db.InsertConnection(request.getUserFromId(), request.getUserToId());

        if(result) {
            id = db.NotifyAccept(request.getUserFromId(), request.getUserToId());
        }
        else{
            id = -1;
        }

        return new AcceptConnectionResponse(id);
    }

    public RejectConnectionResponse RejectConnection(RejectConnectionRequest request) {

        boolean result = db.DeleteConnectionRequest(request.getUserFromId(), request.getUserToId());

        return new RejectConnectionResponse(result);
    }

    public PostExperienceResponse PostExperience(PostExperienceRequest request) {

        long id = DbQueriesHelper.GetLastExperienceId();

        ExperienceEntity exp = DbQueriesHelper.CreateExperience(new ExperienceEntity(), id, request.getUserId(), request.getCompanyTitle(), request.getPosition(), request.getDateFrom(), request.getDateTo());

        id = db.InsertExperience(exp);

        return new PostExperienceResponse(id);
    }

    public PostEducationResponse PostEducation(PostEducationRequest request) {

        long id = DbQueriesHelper.GetLastEducationId();

        EducationEntity edu = DbQueriesHelper.CreateEducation(new EducationEntity(), id, request.getUserId(), request.getInstitution(), request.getDegree(), request.getYearFrom(), request.getYearTo());

        id = db.InsertEducation(edu);

        return new PostEducationResponse(id);
    }

    public PostSkillResponse PostSkill(PostSkillRequest request) {

        long id = DbQueriesHelper.GetLastSkillId();

        SkillsEntity sk = DbQueriesHelper.CreateSkill(new SkillsEntity(), id, request.getUserId(), request.getSkill());

        id = db.InsertSkill(sk);

        return new PostSkillResponse(id);
    }

    public GetPersonalInfoResponse GetPersonalInfo(GetConnectionRequestsRequest request) {

        List<ExperienceEntity> experience = db.GetExperience(request.getUserId());
        List<EducationEntity> education = db.GetEducation(request.getUserId());
        List<SkillsEntity> skills = db.GetSkills(request.getUserId());

        return new GetPersonalInfoResponse(experience, education, skills);
    }

    public UpdateExperienceResponse UpdateExperience(UpdateExperienceRequest request) {

        boolean result = db.UpdateExperience(request.getExperienceId(), request.getCompanyTitle(), request.getPosition(), request.getDateFrom(), request.getDateTo());

        return new UpdateExperienceResponse(result);
    }

    public UpdateEducationResponse UpdateEducation(UpdateEducationRequest request) {

        boolean result = db.UpdateEducation(request.getEducationId(), request.getInstitution(), request.getDegree(), request.getYearFrom(), request.getYearTo());

        return new UpdateEducationResponse(result);
    }

    public UpdateSkillResponse UpdateSkill(UpdateSkillRequest request) {

        boolean result = db.UpdateSkill(request.getSkillId(), request.getSkill());

        return new UpdateSkillResponse(result);
    }

    public SendMessageResponse SendMessage(SendMessageRequest request) {

        MessagesEntity message = DbQueriesHelper.CreateMessage(new MessagesEntity(), request.getUserFrom(), request.getUserTo(), request.getMessage());

        boolean result = db.InsertMessage(message);

        return new SendMessageResponse(result);
    }

    public GetMessagesResponse GetMessages(GetMessagesRequest request) {

        List<MessagesEntity> messages = db.GetMessages(request.getUserId(), request.getFriendId());

        return new GetMessagesResponse(messages);
    }

    public GetNotificationsResponse GetNotifications(GetNotificationsRequest request) {

        List<NotificationsEntity> notifications = db.GetNotifications(request.getUserId());

        return new GetNotificationsResponse(notifications);
    }

    public GetConversationsResponse GetConversations(GetConversationsRequest request) {

        List<UserInfo> result = new ArrayList<UserInfo>();

        List<UsersEntity> users = db.GetConversations(request.getUserId());

        for(UsersEntity u: users){

            PicturesEntity picture = db.GetPicture(u.getId());
            result.add(new UserInfo(u.getFirstName(), u.getLastName(), u.getEmail(), picture.getLink()));
        }

        return new GetConversationsResponse(result);
    }

    public UpdateEmailResponse UpdateEmail(UpdateEmailRequest request) {

        UsersEntity user = db.GetUserById(request.getUserId());

        if(!user.getPassword().equals(request.getPassword())){

            return new UpdateEmailResponse(0);

        }

        long userId = db.UpdateEmail(request.getUserId(), request.getEmail());

        return new UpdateEmailResponse(userId);
    }

    public UpdatePasswordResponse UpdatePassword(UpdatePasswordRequest request) {

        UsersEntity user = db.GetUserById(request.getUserId());

        if(!user.getPassword().equals(request.getOldPassword())){

            return new UpdatePasswordResponse(0);

        }

        long id = db.UpdatePassword(request.getUserId(), request.getNewPassword());

        return new UpdatePasswordResponse(id);
    }
}
