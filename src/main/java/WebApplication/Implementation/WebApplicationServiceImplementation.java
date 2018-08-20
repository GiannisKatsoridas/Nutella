package WebApplication.Implementation;

import WebApplication.Implementation.Database.DbQueries;
import WebApplication.Implementation.Database.DbQueriesHelper;
import WebApplication.Implementation.Database.JPAResource;
import WebApplication.Interface.*;
import WebApplication.Model.Entities.*;
import WebApplication.Model.Helpers.Article;
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

        return new GetInfoResponse(user.getFirstName(), user.getLastName(), user.getEmail(), picture.getLink());
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

        List<UsersEntity> users = db.GetConnections(request.getUserId());

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

        return new LikeResponse(result);
    }

    public CommentResponse Comment(CommentRequest request) {

        CommentsEntity like = DbQueriesHelper.CreateComment(new CommentsEntity(), request);

        boolean result = db.Comment(like);

        return new CommentResponse(result);
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
}
