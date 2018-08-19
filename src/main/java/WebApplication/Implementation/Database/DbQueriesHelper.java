package WebApplication.Implementation.Database;

import WebApplication.Model.Entities.PicturesEntity;
import WebApplication.Model.Entities.UsersEntity;
import WebApplication.Model.Requests.*;
import WebApplication.Model.Responses.RegisterResponse;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class DbQueriesHelper {

    public static UsersEntity createUsersEntity(UsersEntity user, RegisterRequest request){

        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setIsAdmin(false);
        user.setPhone(request.getPhone());
        user.setPassword(request.getPassword());

        return user;
    }

    public static long getLastUserId(EntityManager em){

        Query query = em.createQuery("select t from UsersEntity t order by t.id desc");
        List<UsersEntity> results = query.setMaxResults(1).getResultList();

        return results.size() == 0 ? 1000000001 : results.get(0).getId()+1;

    }

    public static PicturesEntity createPicturesEntity(PicturesEntity picture, long userId, String link){

        picture.setUserId(userId);
        picture.setLink(link);

        return picture;

    }

}
