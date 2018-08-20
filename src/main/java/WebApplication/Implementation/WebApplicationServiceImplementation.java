package WebApplication.Implementation;

import WebApplication.Implementation.Database.DbQueries;
import WebApplication.Implementation.Database.DbQueriesHelper;
import WebApplication.Implementation.Database.JPAResource;
import WebApplication.Interface.*;
import WebApplication.Model.Entities.MediaEntity;
import WebApplication.Model.Entities.PicturesEntity;
import WebApplication.Model.Entities.PostsEntity;
import WebApplication.Model.Entities.UsersEntity;
import WebApplication.Model.Requests.*;
import WebApplication.Model.Responses.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
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
        em.getTransaction().begin();

        long postId = db.InsertPost(em, post);

        List<String> media = request.getMedia();

        for(int i=0; i<media.size() && postId!=-1; i++){
            postId = db.InsertMedia(em, postId, media.get(i));
        }

        if(postId != -1){
            em.getTransaction().commit();
        }
        else{
            em.getTransaction().rollback();
        }

        return new InsertPostResponse(postId);
    }

    public GetConnectionsResponse GetConnections(GetConnectionsRequest request){

        List<UsersEntity> users = db.GetConnections(request.getUserId());

        return new GetConnectionsResponse(users);

    }
}
