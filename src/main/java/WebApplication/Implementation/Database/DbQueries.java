package WebApplication.Implementation.Database;

import WebApplication.Implementation.Optimizations.KNN;
import WebApplication.Model.Helpers.Article;
import WebApplication.Model.Helpers.JobSkillsAlike;
import WebApplication.Model.Helpers.UserInfo;
import WebApplication.Model.Entities.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

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
        catch(Exception e){

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
        catch(Exception e){
            id = -1;
        }

        return id;
    }


    public long InsertMedia(EntityManager em, long postId, String link){

        MediaEntity media = DbQueriesHelper.CreateMedia(new MediaEntity(), postId, link);

        try {
            em.persist(media);
        }
        catch (Exception e){
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
            userInfo.add(new UserInfo(u.getId(), u.getFirstName(), u.getLastName(), u.getEmail(), image));

        }

        return userInfo;

    }


    public long InsertJob(EntityManager em, JobsEntity job){

        long jobId;

        try{
            em.persist(job);
            jobId = job.getId();
        }
        catch(Exception e){
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
        catch(Exception e){

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
        catch(Exception e){

            result = false;
        }

        em.close();

        return result;
    }


    public List<PostsEntity> GetPostsFromFriends(long userId){

        EntityManager em = JPAResource.factory.createEntityManager();

        List<PostsEntity> posts1 = em.createNamedQuery("PostsEntity.GetPostsFromFriend1").setParameter("userId", userId).getResultList();
        List<PostsEntity> posts2 = em.createNamedQuery("PostsEntity.GetPostsFromFriend2").setParameter("userId", userId).getResultList();

        em.close();

        posts1.addAll(posts2);

        return posts1;

    }


    public List<PostsEntity> GetPostsFromNeighbors(long userId){

        EntityManager em = JPAResource.factory.createEntityManager();

        ArrayList<Long> closestNeighbors = KNN.findKNearestNeighborsPosts(userId);
        ArrayList<PostsEntity> posts = new ArrayList<PostsEntity>();

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -3);

        java.sql.Timestamp d = new java.sql.Timestamp(cal.getTimeInMillis());


        for(Long n: closestNeighbors){

            List<PostsEntity> differentPostsLikes = em.createNamedQuery("PostsEntity.getDifferentPostsFromNeighborsLikes").setParameter("userId", userId).setParameter("neighborId", n).setParameter("timestamp", d).getResultList();
            List<PostsEntity> differentPostsComments = em.createNamedQuery("PostsEntity.getDifferentPostsFromNeighborsComments").setParameter("userId", userId).setParameter("neighborId", n).setParameter("timestamp", d).getResultList();
            for(PostsEntity j: differentPostsLikes){
                if(!posts.contains(j))
                    posts.add(j);
            }
            for(PostsEntity j: differentPostsComments){
                if(!posts.contains(j))
                    posts.add(j);
            }

        }

        em.close();

        return posts;
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
            userInfo.add(new UserInfo(u.getId(), u.getFirstName(), u.getLastName(), u.getEmail(), image));

        }

        return userInfo;
    }


    public List<JobsEntity> GetJobsFromFriends(long userId){

        EntityManager em = JPAResource.factory.createEntityManager();

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -3);

        java.sql.Date d = new java.sql.Date(cal.getTimeInMillis());


        List<JobsEntity> results1 = em.createNamedQuery("JobsEntity.getJobsFrom1").setParameter("userId", userId).setParameter("date", d, TemporalType.DATE).getResultList();
        List<JobsEntity> results2 = em.createNamedQuery("JobsEntity.getJobsFrom2").setParameter("userId", userId).setParameter("date", d, TemporalType.DATE).getResultList();

        results1.addAll(results2);

        em.close();

        return results1;
    }


    public List<JobsEntity> GetJobsAlike(long userId){

        EntityManager em = JPAResource.factory.createEntityManager();

        List<SkillsEntity> skills = em.createNamedQuery("SkillsEntity.GetSkills").setParameter("userId", userId).getResultList();
        List<JobsEntity> jobs = em.createNamedQuery("JobsEntity.selectAllJobs").getResultList();

        List<JobSkillsAlike> jobsAndSkills = DbQueriesHelper.GetCommonJobSkills(new ArrayList<JobSkillsAlike>(), skills, jobs, em);

        Comparator comp = new Comparator<JobSkillsAlike>() {
            public int compare(JobSkillsAlike o, JobSkillsAlike t1) {
                return t1.getSkillsAlike() - o.getSkillsAlike();
            }
        };

        Collections.sort(jobsAndSkills, comp);

        long[] ids = new long[5];

        for(int i=4; i>=0; i--){

            if(jobsAndSkills.size() <= i){
                ids[i] = 0;
            }
            else{
                ids[i] = jobsAndSkills.get(i).getJobId();
            }

        }

        List<JobsEntity> result = em.createNamedQuery("JobsEntity.getFiveSpecificJobs").setParameter("id1", ids[0]).setParameter("id2", ids[1]).setParameter("id3", ids[2]).setParameter("id4", ids[3]).setParameter("id5", ids[4]).getResultList();

        return result;
    }


    public List<JobsEntity> GetJobsFromClosestNeighbors(long userId){

        EntityManager em = JPAResource.factory.createEntityManager();

        ArrayList<Long> closestNeighbors = KNN.findKNearestNeighborsJobs(userId);
        ArrayList<JobsEntity> jobs = new ArrayList<JobsEntity>();

        for(Long l: closestNeighbors){

            List<JobsEntity> differentJobs = em.createNamedQuery("JobsEntity.getDifferentJobsFromNeighbors").setParameter("userId", userId).setParameter("neighborId", l).getResultList();
            for(JobsEntity j: differentJobs){
                if(!jobs.contains(j))
                    jobs.add(j);
            }

        }

        em.close();

        return jobs;
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
        catch (Exception e){

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
            result.add(new UserInfo(u.getId(), u.getFirstName(), u.getLastName(), u.getEmail(), image));
        }

        em.close();

        return result;
    }


    public List<JobsEntity> GetJobsByUser(long userId){

        EntityManager em = JPAResource.factory.createEntityManager();

        List<JobsEntity> jobs = em.createNamedQuery("JobsEntity.getJobByUser").setParameter("userId", userId).getResultList();

        em.close();

        return jobs;
    }


    public boolean EditJob(long jobId, String jobTitle, String jobDescription){

        boolean result;

        EntityManager em = JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        JobsEntity job = DbQueriesHelper.GetJobById(em, jobId);

        tx.begin();
        try{
            job.setTitle(jobTitle);
            job.setDescription(jobDescription);
            tx.commit();
            result = true;
        }
        catch (Exception e){

            result = false;
        }

        em.close();

        return result;
    }


    public boolean InsertConnectionRequest(FriendrequestEntity fr){

        boolean success;

        EntityManager em = JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            em.persist(fr);
            tx.commit();
            success = true;
        }
        catch (Exception e){

            success = false;
        }

        return success;

    }


    private long InsertNotification(NotificationsEntity notification){

        long result;

        EntityManager em = JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            em.persist(notification);
            tx.commit();
            result = notification.getId();
        }
        catch (Exception e){

            result = -1;
        }

        em.close();

        return result;
    }


    public long NotifyLike(long userId, long postId){

        long id = DbQueriesHelper.GetLastNotificationId();
        long userTo = DbQueriesHelper.GetUserNotifiedLikes(postId);
        java.sql.Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        NotificationsEntity not = DbQueriesHelper.CreateNotification(new NotificationsEntity(), id, userId, userTo, 1, timestamp, postId);

        long notId = InsertNotification(not);

        return notId;
    }


    public long NotifyComment(long userId, long postId){

        long id = DbQueriesHelper.GetLastNotificationId();
        long userTo = DbQueriesHelper.GetUserNotifiedComments(postId);
        java.sql.Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        NotificationsEntity not = DbQueriesHelper.CreateNotification(new NotificationsEntity(), id, userId, userTo, 2, timestamp, postId);

        long notId = InsertNotification(not);

        return notId;
    }


    public long NotifyConnectionRequest(long sender, long receiver){

        long id = DbQueriesHelper.GetLastNotificationId();
        java.sql.Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        NotificationsEntity not = DbQueriesHelper.CreateNotification(new NotificationsEntity(), id, sender, receiver, 3, timestamp, 0);

        long notId = InsertNotification(not);

        return notId;
    }


    public List<UsersEntity> GetConnectionRequests(long userId){

        EntityManager em = JPAResource.factory.createEntityManager();

        List<UsersEntity> users = em.createNamedQuery("FriendrequestsEntity.getConnectionRequests").setParameter("userId", userId).getResultList();

        em.close();

        return users;
    }


    public boolean DeleteConnectionRequest(long sender, long receiver){

        FriendrequestEntityPK keys = new FriendrequestEntityPK();
        keys.setSender(sender);
        keys.setReceiver(receiver);

        boolean result;

        EntityManager em = JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        FriendrequestEntity fr = em.find(FriendrequestEntity.class, keys);

        tx.begin();

        try{
            em.remove(fr);
            tx.commit();
            result = true;
        }
        catch (Exception e){

            result = false;
        }

        em.close();

        return result;
    }


    public boolean InsertConnection(long sender, long receiver){

        boolean result;

        EntityManager em = JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        FriendsEntity f = DbQueriesHelper.CreateFriends(new FriendsEntity(), sender, receiver);

        tx.begin();

        try{
            em.persist(f);
            tx.commit();
            result = true;
        }
        catch (Exception e){

            result = false;
        }

        em.close();

        return result;
    }


    public long NotifyAccept(long sender, long receiver){

        long id = DbQueriesHelper.GetLastNotificationId();
        java.sql.Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        NotificationsEntity not = DbQueriesHelper.CreateNotification(new NotificationsEntity(), id, sender, receiver, 4, timestamp, 0);

        long notId = InsertNotification(not);

        return notId;
    }


    public long InsertExperience(ExperienceEntity exp){

        long result;

        EntityManager em = JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            em.persist(exp);
            tx.commit();
            result = exp.getExperienceId();
        }
        catch (Exception e){

            result = -1;
        }

        em.close();

        return result;
    }


    public long InsertEducation(EducationEntity edu){

        long result;

        EntityManager em = JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            em.persist(edu);
            tx.commit();
            result = edu.getEducationId();
        }
        catch (Exception e){

            result = -1;
        }

        em.close();

        return result;
    }


    public long InsertSkill(SkillsEntity sk){

        long result;

        EntityManager em = JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            em.persist(sk);
            tx.commit();
            result = sk.getSkillId();
        }
        catch (Exception e){

            result = -1;
        }

        em.close();

        return result;
    }


    public List<ExperienceEntity> GetExperience(long userId){

        EntityManager em = JPAResource.factory.createEntityManager();

        List<ExperienceEntity> experience = em.createNamedQuery("ExperienceEntity.GetExperience").setParameter("userId", userId).getResultList();

        em.close();

        return experience;
    }


    public List<EducationEntity> GetEducation(long userId){

        EntityManager em = JPAResource.factory.createEntityManager();

        List<EducationEntity> education = em.createNamedQuery("EducationEntity.GetEducation").setParameter("userId", userId).getResultList();

        em.close();

        return education;
    }


    public List<SkillsEntity> GetSkills(long userId){

        EntityManager em = JPAResource.factory.createEntityManager();

        List<SkillsEntity> skills = em.createNamedQuery("SkillsEntity.GetSkills").setParameter("userId", userId).getResultList();

        em.close();

        return skills;
    }


    public boolean UpdateExperience(long experienceId, String companyTitle, String position, java.sql.Date dateFrom, java.sql.Date dateTo){

        boolean result;

        EntityManager em = JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        ExperienceEntity exp = em.find(ExperienceEntity.class, experienceId);

        try{
            DbQueriesHelper.UpdateExperience(exp, companyTitle, position, dateFrom, dateTo);
            tx.commit();
            result = true;
        }
        catch (Exception e){

            result = false;
        }

        em.close();

        return result;
    }


    public boolean UpdateEducation(long educationId, String institution, String degree, String yearFrom, String yearTo){

        boolean result;

        EntityManager em = JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        EducationEntity education = em.find(EducationEntity.class, educationId);

        try{
            DbQueriesHelper.UpdateEducation(education, institution, degree, yearFrom, yearTo);
            tx.commit();
            result = true;
        }
        catch (Exception e){

            result = false;
        }

        em.close();

        return result;
    }


    public boolean UpdateSkill(long skillId, String skill){

        boolean result;

        EntityManager em = JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        SkillsEntity sk = em.find(SkillsEntity.class, skillId);

        try{
            DbQueriesHelper.UpdateSkill(sk, skill);
            tx.commit();
            result = true;
        }
        catch (Exception e){

            result = false;
        }

        em.close();

        return result;
    }


    public boolean InsertMessage(MessagesEntity message){

        boolean result;

        EntityManager em = JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            em.persist(message);
            tx.commit();
            result = true;
        }
        catch (Exception e){

            result = false;
        }

        em.close();

        return result;
    }


    public List<MessagesEntity> GetMessages(long userId, long friendId){

        EntityManager em = JPAResource.factory.createEntityManager();

        List<MessagesEntity> messages = em.createNamedQuery("MessagesEntity.GetMessages").setParameter("userId", userId).setParameter("friendId", friendId).getResultList();

        em.close();

        return messages;
    }


    public List<NotificationsEntity> GetNotifications(long userId){

        EntityManager em = JPAResource.factory.createEntityManager();

        List<NotificationsEntity> notifications = em.createNamedQuery("NotificationsEntity.GetNotifications").setParameter("userId", userId).getResultList();

        em.close();

        return notifications;
    }


    public List<UsersEntity> GetConversations(long userId){

        EntityManager em = JPAResource.factory.createEntityManager();

        List<UsersEntity> collection = em.createNamedQuery("UsersEntity.getConversations").setParameter("userId", userId).getResultList();

        Set<UsersEntity> set = new LinkedHashSet<UsersEntity>(collection);

        collection.clear();
        collection.addAll(set);

        em.close();

        return collection;
    }


    public long UpdateEmail(long userId, String email){

        long id;

        EntityManager em = JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        UsersEntity user = em.find(UsersEntity.class, userId);

        try{
            user.setEmail(email);
            tx.commit();
            id = userId;
        }
        catch (Exception e){

            id = -1;
        }

        em.close();

        return id;
    }


    public long UpdatePassword(long userId, String password){

        long id;

        EntityManager em = JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        UsersEntity user = em.find(UsersEntity.class, userId);

        try{
            user.setPassword(password);
            tx.commit();
            id = userId;
        }
        catch (Exception e){

            id = -1;
        }

        em.close();

        return id;
    }


    public boolean InsertJobRequirement(EntityManager em, JobrequirementsEntity jr){

        boolean result;

        try{
            em.persist(jr);
            result = true;
        }
        catch (Exception e){
            result = false;
        }

        return result;
    }


    public List<JobapplicationsEntity> GetMyApplications(long userId){

        List<JobapplicationsEntity> jobs;

        EntityManager em = JPAResource.factory.createEntityManager();

        try {
            jobs = em.createNamedQuery("JobapplicationsEntity.GetJobsByUser").setParameter("userId", userId).getResultList();
        }
        catch(Exception ex){
            jobs = new ArrayList<JobapplicationsEntity>();
        }

        em.close();

        return jobs;
    }


    public Article GetPost(long postId) {

        EntityManager em = JPAResource.factory.createEntityManager();

        List<PostsEntity> posts = em.createNamedQuery("PostsEntity.GetPost").setParameter("postId", postId).setMaxResults(1).getResultList();
        List<LikesEntity> likes = em.createNamedQuery("LikesEntity.GetLikes").setParameter("postId", postId).getResultList();
        List<CommentsEntity> comments = em.createNamedQuery("CommentsEntity.GetComments").setParameter("postId", postId).getResultList();

        em.close();

        return new Article(postId, posts.get(0).getUserId(), posts.get(0).getText(), likes, comments);
    }


    public String InsertSingleImage(long userId, String link) {

        PicturesEntity pic = DbQueriesHelper.CreatePicturesEntity(new PicturesEntity(), userId, link);

        EntityManager em = JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {

            PicturesEntity p = em.find(PicturesEntity.class, userId);

            tx.begin();

            if (p == null) {
                em.persist(pic);
            } else {
                p.setLink(link);
            }

            tx.commit();
        }
        catch (Exception ex){
            return "null";
        }

        em.close();

        return link;
    }


    public List<PostsEntity> GetMyPosts(long userId) {

        EntityManager em = JPAResource.factory.createEntityManager();

        List<PostsEntity> posts = em.createNamedQuery("PostsEntity.getPostsByUser").setParameter("userId", userId).getResultList();

        em.close();

        return posts;
    }


    public List<LikesEntity> GetMyLikes(long userId) {

        EntityManager em = JPAResource.factory.createEntityManager();

        List<LikesEntity> likes = em.createNamedQuery("LikesEntity.getLikesByUser").setParameter("userId", userId).getResultList();

        em.close();

        return likes;
    }


    public List<CommentsEntity> GetMyComments(long userId) {

        EntityManager em = JPAResource.factory.createEntityManager();

        List<CommentsEntity> comments = em.createNamedQuery("CommentsEntity.getCommentsByUser").setParameter("userId", userId).getResultList();

        em.close();

        return comments;
    }

    public String GetPictureLink(long userId) {

        EntityManager em = JPAResource.factory.createEntityManager();

        List<PicturesEntity> pics = em.createNamedQuery("PicturesEntity.getPictureFromUser").setParameter("userId", userId).getResultList();

        PicturesEntity pic = pics.get(0);

        return pic.getLink();
    }
}
