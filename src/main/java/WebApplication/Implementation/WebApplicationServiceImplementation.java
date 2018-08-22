package WebApplication.Implementation;

import WebApplication.Implementation.Database.DbQueries;
import WebApplication.Implementation.Database.DbQueriesHelper;
import WebApplication.Implementation.Database.JPAResource;
import WebApplication.Interface.*;
import WebApplication.Model.Entities.*;
import WebApplication.Model.Helpers.Article;
import WebApplication.Model.Helpers.UserInfo;
import WebApplication.Model.Requests.*;
import WebApplication.Model.Responses.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
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

        return new InsertPostResponse(postId);
    }

    public GetConnectionsResponse GetConnections(GetConnectionsRequest request){

        List<UserInfo> users = db.GetConnections(request.getUserId());

        return new GetConnectionsResponse(users);

    }

    public InsertJobResponse InsertJob(InsertJobRequest request) {

        JobsEntity job = DbQueriesHelper.CreateJob(new JobsEntity(), request);

        EntityManager em = JPAResource.factory.createEntityManager();

        long jobId = DbQueriesHelper.GetLastJobId(em);
        job.setId(jobId);

        jobId = db.InsertJob(em, job);

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

        return new CommentResponse(id);
    }

    public GetPostsResponse GetPosts(GetPostsRequest request) {

        List<PostsEntity> posts = db.GetPosts(request.getUserId());

        List<Article> articles = new ArrayList<Article>();

        List<LikesEntity> likes;
        List<CommentsEntity> comments;

        for(int i=0; i<posts.size(); i++){

            likes = db.GetLikes(posts.get(i).getId());
            comments = db.GetComments(posts.get(i).getId());
            articles.add(new Article(posts.get(i).getText(), likes, comments));

        }

        return new GetPostsResponse(articles);
    }

    public SearchResponse Search(SearchRequest request) {

        List<UserInfo> results = db.Search(request.getQuery());

        return new SearchResponse(results);
    }

    public GetJobsResponse GetJobs(GetJobsRequest request) {

        List<JobsEntity> results = db.GetJobsForUser(request.getUserId());

        return new GetJobsResponse(results);
    }

    public JobApplicationResponse JobApplication(JobApplicationRequest request) {

        JobapplicationsEntity ja = DbQueriesHelper.CreateJobApplication(new JobapplicationsEntity(), request.getUserId(), request.getJobId());

        boolean success = db.InsertJobApplication(ja);

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
}
