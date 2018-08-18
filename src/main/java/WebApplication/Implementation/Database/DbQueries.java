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

        Query query = em.createQuery("select t from UsersEntity t order by t.id desc");
        List<UsersEntity> results = query.setMaxResults(1).getResultList();

        id = results.size() == 0 ? 1000000001 : results.get(0).getId()+1;

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

        return new RegisterResponse(id);
    }

}
