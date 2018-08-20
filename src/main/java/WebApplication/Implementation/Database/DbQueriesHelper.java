package WebApplication.Implementation.Database;

import WebApplication.Model.Entities.MediaEntity;
import WebApplication.Model.Entities.PicturesEntity;
import WebApplication.Model.Entities.PostsEntity;
import WebApplication.Model.Entities.UsersEntity;
import WebApplication.Model.Requests.*;
import WebApplication.Model.Responses.RegisterResponse;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class DbQueriesHelper {

    public static UsersEntity CreateUsersEntity(UsersEntity user, RegisterRequest request){

        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setIsAdmin(false);
        user.setPhone(request.getPhone());
        user.setPassword(request.getPassword());

        return user;
    }

    public static List<UsersEntity> GetUsersByEmail(EntityManager em, String email){

        List<UsersEntity> usersByEmail = em.createNamedQuery("UsersEntity.findByEmail").setParameter("email", email).getResultList();

        return usersByEmail;
    }

    public static long GetLastUserId(EntityManager em){

        Query query = em.createQuery("select t from UsersEntity t order by t.id desc");
        List<UsersEntity> results = query.setMaxResults(1).getResultList();

        return results.size() == 0 ? 1000000001 : results.get(0).getId()+1;

    }

    public static PicturesEntity CreatePicturesEntity(PicturesEntity picture, long userId, String link){

        picture.setUserId(userId);
        picture.setLink(link);

        return picture;

    }

    public static PostsEntity CreatePost(PostsEntity post, InsertPostRequest request){

        post.setUserId(request.getUserId());
        post.setText(request.getText());
        post.setDate(new java.sql.Date(new java.util.Date().getTime()));

        return post;
    }

    public static long GetLastPostId(EntityManager em){

        Query query = em.createQuery("select p from PostsEntity p order by p.id desc");
        List<UsersEntity> results = query.setMaxResults(1).getResultList();

        return results.size() == 0 ? 2000000001 : results.get(0).getId()+1;

    }

    public static MediaEntity CreateMedia(MediaEntity media, long postId, String link){

        media.setLink(link);
        media.setPostId(postId);

        return media;
    }

}
