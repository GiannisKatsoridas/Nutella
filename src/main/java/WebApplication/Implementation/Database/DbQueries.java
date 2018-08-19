package WebApplication.Implementation.Database;

import WebApplication.Model.Requests.*;
import WebApplication.Model.Responses.*;
import WebApplication.Model.Entities.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

public class DbQueries {

    public RegisterResponse CreateUser(RegisterRequest request){

        long id;

        UsersEntity user = DbQueriesHelper.createUsersEntity(new UsersEntity(), request);

        EntityManager em = JPAResource.factory.createEntityManager();

        List<UsersEntity> usersByEmail = em.createNamedQuery("UsersEntity.findByEmail").setParameter("email", request.getEmail()).getResultList();
        if(usersByEmail.size() != 0){
            return new RegisterResponse(0);
        }

        id = DbQueriesHelper.getLastUserId(em);

        user.setId(id);

        PicturesEntity picture = DbQueriesHelper.createPicturesEntity(new PicturesEntity(), id, request.getImage());

        em.getTransaction().begin();

        try {
            em.persist(user);
            em.persist(picture);
            em.getTransaction().commit();
        }
        catch(PersistenceException e){
            em.getTransaction().rollback();
            id = -2;
        }

        return new RegisterResponse(id);
    }


    public LoginResponse Login(LoginRequest request){

        EntityManager em = JPAResource.factory.createEntityManager();

        List<UsersEntity> users = em.createNamedQuery("UsersEntity.loginUser").setParameter("email", request.getEmail()).setParameter("password", request.getPassword()).getResultList();

        if(users.size() > 1){
            return new LoginResponse(-1, false);
        }
        else if(users.size() == 0){
            return new LoginResponse(0, false);
        }
        else{
            return new LoginResponse(users.get(0).getId(), users.get(0).getIsAdmin());
        }

    }


    public GetUsersListResponse GetUsersList(){

        EntityManager em = JPAResource.factory.createEntityManager();

        List<UsersEntity> users = em.createNamedQuery("UsersEntity.getAllUsers").getResultList();

        return new GetUsersListResponse(users);

    }


    public GetInfoResponse GetInfo(GetInfoRequest request){

        EntityManager em = JPAResource.factory.createEntityManager();

        UsersEntity user;
        PicturesEntity picture;

        try {
            user = em.find(UsersEntity.class, request.getUserId());
            List<PicturesEntity> pictures = em.createNamedQuery("PicturesEntity.getPictureFromUser").setParameter("userId", request.getUserId()).getResultList();
            picture = pictures.get(0);
        }
        catch (PersistenceException e){
            return new GetInfoResponse(null, null, null, null);
        }

        return new GetInfoResponse(user.getFirstName(), user.getLastName(), user.getEmail(), picture.getLink());


    }

}
