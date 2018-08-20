package WebApplication.Implementation.Database;

import WebApplication.Model.Requests.*;
import WebApplication.Model.Responses.*;
import WebApplication.Model.Entities.*;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

public class DbQueries {


    public long InsertUser(UsersEntity user){

        EntityManager em = JPAResource.factory.createEntityManager();

        List<UsersEntity> usersByEmail = DbQueriesHelper.getUsersByEmail(em, user.getEmail());
        if(usersByEmail.size() != 0){
            return 0;
        }

        long id = DbQueriesHelper.getLastUserId(em);

        user.setId(id);

        em.getTransaction().begin();

        try {
            em.persist(user);
            em.getTransaction().commit();
        }
        catch(PersistenceException e){
            em.getTransaction().rollback();
            id = -2;
        }

        em.close();

        return id;

    }


    public long InsertPicture(PicturesEntity picture){

        long id = picture.getUserId();

        EntityManager em = JPAResource.factory.createEntityManager();

        em.getTransaction().begin();

        try {
            em.persist(picture);
            em.getTransaction().commit();
        }
        catch(PersistenceException e){
            em.getTransaction().rollback();
            id = -2;
        }

        em.close();

        return id;
    }


    public UsersEntity FindUserByEmailPassword(String email, String password){

        EntityManager em = JPAResource.factory.createEntityManager();

        List<UsersEntity> users = em.createNamedQuery("UsersEntity.loginUser").setParameter("email", email).setParameter("password", password).getResultList();

        em.close();

        if(users.size() > 1){
            UsersEntity returned = new UsersEntity();
            returned.setId(-1);
            return returned;
        }
        else if(users.size() == 0){
            UsersEntity returned = new UsersEntity();
            returned.setId(0);
            return returned;
        }
        else{
            return users.get(0);
        }

    }


    public List<UsersEntity> GetUsersList(){

        EntityManager em = JPAResource.factory.createEntityManager();

        List<UsersEntity> users = em.createNamedQuery("UsersEntity.getAllUsers").getResultList();

        em.close();

        return users;

    }


    public UsersEntity GetUserById(long id){

        EntityManager em = JPAResource.factory.createEntityManager();

        UsersEntity user = em.find(UsersEntity.class, id);

        em.close();

        return user;
    }


    public PicturesEntity GetPicture(long id){

        EntityManager em = JPAResource.factory.createEntityManager();

        List<PicturesEntity> pictures = em.createNamedQuery("PicturesEntity.getPictureFromUser").setParameter("userId", id).getResultList();

        em.close();

        return pictures.get(0);
    }

}
