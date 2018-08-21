package WebApplication.Implementation.Database;

import WebApplication.Model.Helpers.UserInfo;
import WebApplication.Model.Requests.*;
import WebApplication.Model.Responses.*;
import WebApplication.Model.Entities.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class DbQueries {


    public long InsertUser(UsersEntity user){

        EntityManager em = JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        List<UsersEntity> usersByEmail = DbQueriesHelper.GetUsersByEmail(em, user.getEmail());
        if(usersByEmail.size() != 0){
            return 0;
        }

        long id = DbQueriesHelper.GetLastUserId(em);

        user.setId(id);

        tx.begin();

        try {
            em.persist(user);
            tx.commit();
        }
        catch(PersistenceException e){
            tx.rollback();
            id = -2;
        }

        em.close();

        return id;

    }


    public long InsertPicture(PicturesEntity picture){

        long id = picture.getUserId();

        EntityManager em = JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            em.persist(picture);
            tx.commit();
        }
        catch(PersistenceException e){
            tx.rollback();
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


    public long InsertPost(EntityManager em, PostsEntity post){

        long id = DbQueriesHelper.GetLastPostId(em);
        post.setId(id);

        try{
            em.persist(post);
        }
        catch(PersistenceException e){
            id = -1;
        }

        return id;
    }


    public long InsertMedia(EntityManager em, long postId, String link){

        MediaEntity media = DbQueriesHelper.CreateMedia(new MediaEntity(), postId, link);

        try {
            em.persist(media);
        }
        catch (PersistenceException e){
            postId = -1;
        }

        return postId;
    }


    public List<UserInfo> GetConnections(long userId){

        List<UserInfo> userInfo = new ArrayList<UserInfo>();

        EntityManager em = JPAResource.factory.createEntityManager();

        List<UsersEntity> connections1 = em.createNamedQuery("FriendsEntity.GetFriendsFromUser1").setParameter("userId", userId).getResultList();
        List<UsersEntity> connections2 = em.createNamedQuery("FriendsEntity.GetFriendsFromUser2").setParameter("userId", userId).getResultList();

        connections1.addAll(connections2);

        em.close();

        for(UsersEntity u: connections1){

            String image = GetPicture(u.getId()).getLink();
            userInfo.add(new UserInfo(u.getFirstName(), u.getLastName(), u.getEmail(), image));

        }

        return userInfo;

    }


    public long InsertJob(EntityManager em, JobsEntity job){

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        long jobId;

        try{
            em.persist(job);
            tx.commit();
            jobId = job.getId();
        }
        catch(PersistenceException e){
            tx.rollback();
            jobId = -1;
        }

        return jobId;
    }


    public boolean Like(LikesEntity like){

        boolean result;

        EntityManager em = JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            em.persist(like);
            tx.commit();
            result = true;
        }
        catch(PersistenceException e){
            tx.rollback();
            result = false;
        }

        em.close();

        return result;
    }


    public boolean Comment(CommentsEntity comment){

        boolean result;

        EntityManager em = JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            em.persist(comment);
            tx.commit();
            result = true;
        }
        catch(PersistenceException e){
            tx.rollback();
            result = false;
        }

        em.close();

        return result;
    }


    public List<PostsEntity> GetPosts(long userId){

        EntityManager em = JPAResource.factory.createEntityManager();

        List<PostsEntity> posts1 = em.createNamedQuery("PostsEntity.GetPostsFromFriend1").setParameter("userId", userId).getResultList();
        List<PostsEntity> posts2 = em.createNamedQuery("PostsEntity.GetPostsFromFriend2").setParameter("userId", userId).getResultList();

        em.close();

        posts1.addAll(posts2);

        return posts1;

    }


    public List<LikesEntity> GetLikes(long postId){

        EntityManager em = JPAResource.factory.createEntityManager();

        List<LikesEntity> likes = em.createNamedQuery("LikesEntity.GetLikes").setParameter("postId", postId).getResultList();

        em.close();

        return likes;

    }


    public List<CommentsEntity> GetComments(long postId){

        EntityManager em = JPAResource.factory.createEntityManager();

        List<CommentsEntity> comments = em.createNamedQuery("CommentsEntity.GetComments").setParameter("postId", postId).getResultList();

        em.close();

        return comments;

    }


    public List<UserInfo> Search(String query){

        List<UserInfo> userInfo = new ArrayList<UserInfo>();

        EntityManager em = JPAResource.factory.createEntityManager();

        List<UsersEntity> results = em.createNamedQuery("UsersEntity.search").setParameter("search", "%"+query+"%").getResultList();

        em.close();

        for(UsersEntity u: results){

            String image = GetPicture(u.getId()).getLink();
            userInfo.add(new UserInfo(u.getFirstName(), u.getLastName(), u.getEmail(), image));

        }

        return userInfo;
    }


    public List<JobsEntity> GetJobs(long userId){

        EntityManager em = JPAResource.factory.createEntityManager();

        List<JobsEntity> results1 = em.createNamedQuery("JobsEntity.getJobsFrom1").setParameter("userId", userId).getResultList();
        List<JobsEntity> results2 = em.createNamedQuery("JobsEntity.getJobsFrom2").setParameter("userId", userId).getResultList();

        results1.addAll(results2);

        em.close();

        return results1;
    }


    public boolean InsertJobApplication(JobapplicationsEntity ja){

        EntityManager em = JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        boolean result;

        try{
            em.persist(ja);
            tx.commit();
            result = true;
        }
        catch (PersistenceException e){
            tx.rollback();
            result = false;
        }

        em.close();

        return result;
    }


    public List<UserInfo> GetJobApplicants(long jobId){

        List<UserInfo> result = new ArrayList<UserInfo>();

        EntityManager em = JPAResource.factory.createEntityManager();

        List<UsersEntity> users = em.createNamedQuery("JobsEntity.getJobApplicants").setParameter("jobId", jobId).getResultList();

        for(UsersEntity u: users){

            String image = GetPicture(u.getId()).getLink();
            result.add(new UserInfo(u.getFirstName(), u.getLastName(), u.getEmail(), image));
        }

        em.close();

        return result;
    }
}
