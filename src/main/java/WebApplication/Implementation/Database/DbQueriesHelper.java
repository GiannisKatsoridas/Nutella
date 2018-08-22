package WebApplication.Implementation.Database;

import WebApplication.Model.Entities.*;
import WebApplication.Model.Requests.*;
import WebApplication.Model.Responses.RegisterResponse;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Timestamp;
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

        return results.size() == 0 ? 1000000001L : results.get(0).getId()+1;

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
        List<PostsEntity> results = query.setMaxResults(1).getResultList();

        return results.size() == 0 ? 2000000001L : results.get(0).getId()+1;

    }

    public static MediaEntity CreateMedia(MediaEntity media, long postId, String link){

        media.setLink(link);
        media.setPostId(postId);

        return media;
    }

    public static JobsEntity CreateJob(JobsEntity job, InsertJobRequest request){

        job.setCreator(request.getUserId());
        job.setDescription(request.getJobDescription());
        job.setTitle(request.getJobTitle());

        return job;
    }

    public static long GetLastJobId(EntityManager em){

        Query query = em.createQuery("select j from JobsEntity j order by j.id desc");
        List<JobsEntity> results = query.setMaxResults(1).getResultList();

        return results.size() == 0 ? 3000000001L : results.get(0).getId()+1;
    }

    public static LikesEntity CreateLike(LikesEntity like, LikeRequest request){

        like.setPostId(request.getPostId());
        like.setUserId(request.getUserId());

        return like;
    }

    public static CommentsEntity CreateComment(CommentsEntity comment, CommentRequest request){

        comment.setPostId(request.getPostId());
        comment.setUserId(request.getUserId());
        comment.setText(request.getText());
        comment.setTimeStamp(new Timestamp(System.currentTimeMillis()));

        return comment;
    }

    public static JobapplicationsEntity CreateJobApplication(JobapplicationsEntity ja, long userId, long jobId){

        ja.setApplicant(userId);
        ja.setJob(jobId);

        return ja;
    }

    public static JobsEntity GetJobById(EntityManager em, long jobId){

        JobsEntity job = em.find(JobsEntity.class, jobId);

        return job;
    }

    public static FriendrequestEntity CreateConnectionRequest(FriendrequestEntity fr, long sender, long receiver){

        fr.setSender(sender);
        fr.setReceiver(receiver);

        return fr;
    }

    public static NotificationsEntity CreateNotification(NotificationsEntity not, long id, long userFrom, long userTo, int category, Timestamp timestamp, long post){

        not.setId(id);
        not.setCategory(category);
        not.setPost(post);
        not.setUserTo(userTo);
        not.setUserFrom(userFrom);
        not.setTimestamp(timestamp);

        return not;

    }

    public static long GetLastNotificationId(){

        EntityManager em = JPAResource.factory.createEntityManager();

        Query query = em.createQuery("select n from NotificationsEntity n order by n.id desc");
        List<NotificationsEntity> results = query.setMaxResults(1).getResultList();

        em.close();

        return results.size() == 0 ? 4000000001L : results.get(0).getId()+1;
    }

    public static long GetUserNotifiedLikes(long postId){

        EntityManager em = JPAResource.factory.createEntityManager();

        List<UsersEntity> users = em.createNamedQuery("LikesEntity.getUserLiked").setParameter("postId", postId).getResultList();

        em.close();

        return users.get(0).getId();
    }

    public static long GetUserNotifiedComments(long postId){

        EntityManager em = JPAResource.factory.createEntityManager();

        List<UsersEntity> users = em.createNamedQuery("CommentsEntity.getUserCommented").setParameter("postId", postId).getResultList();

        em.close();

        return users.get(0).getId();
    }

    public static FriendsEntity CreateFriends(FriendsEntity f, long sender, long receiver){

        f.setUser1(sender);
        f.setUser2(receiver);

        return f;
    }

    public static long GetLastExperienceId(){

        EntityManager em = JPAResource.factory.createEntityManager();

        Query query = em.createQuery("select e from ExperienceEntity e order by e.experienceId desc");
        List<ExperienceEntity> results = query.setMaxResults(1).getResultList();

        em.close();

        return results.size() == 0 ? 5000000001L : results.get(0).getExperienceId()+1;
    }

    public static ExperienceEntity CreateExperience(ExperienceEntity exp, long experienceId, long userId, String companyTitle, String position, java.sql.Date dateFrom, java.sql.Date dateTo){

        exp.setExperienceId(experienceId);
        exp.setUserId(userId);
        exp.setCompanyTitle(companyTitle);
        exp.setPosition(position);
        exp.setDateFrom(dateFrom);
        exp.setDateTo(dateTo);

        return exp;
    }

    public static long GetLastEducationId(){

        EntityManager em = JPAResource.factory.createEntityManager();

        Query query = em.createQuery("select e from EducationEntity e order by e.educationId desc");
        List<EducationEntity> results = query.setMaxResults(1).getResultList();

        em.close();

        return results.size() == 0 ? 6000000001L : results.get(0).getEducationId()+1;
    }

    public static EducationEntity CreateEducation(EducationEntity edu, long educationId, long userId, String institution, String degree, String yearFrom, String yearTo){

        edu.setEducationId(educationId);
        edu.setUserId(userId);
        edu.setInstitution(institution);
        edu.setDegree(degree);
        edu.setYearFrom(yearFrom);
        edu.setYearTo(yearTo);

        return edu;
    }

    public static long GetLastSkillId(){

        EntityManager em = JPAResource.factory.createEntityManager();

        Query query = em.createQuery("select s from SkillsEntity s order by s.skillId desc");
        List<SkillsEntity> results = query.setMaxResults(1).getResultList();

        em.close();

        return results.size() == 0 ? 7000000001L : results.get(0).getSkillId()+1;
    }

    public static SkillsEntity CreateSkill(SkillsEntity sk, long skillId, long userId, String skill){

        sk.setSkillId(skillId);
        sk.setUserId(userId);
        sk.setSkill(skill);

        return sk;
    }

    public static void UpdateExperience(ExperienceEntity exp, String companyTitle, String position, java.sql.Date dateFrom, java.sql.Date dateTo){

        exp.setCompanyTitle(companyTitle);
        exp.setPosition(position);
        exp.setDateFrom(dateFrom);
        exp.setDateTo(dateTo);
    }

    public static void UpdateEducation(EducationEntity edu, String institution, String degree, String yearFrom, String yearTo){

        edu.setInstitution(institution);
        edu.setDegree(degree);
        edu.setYearFrom(yearFrom);
        edu.setYearTo(yearTo);
    }

    public static void UpdateSkill(SkillsEntity sk, String skill){

        sk.setSkill(skill);
    }
}
