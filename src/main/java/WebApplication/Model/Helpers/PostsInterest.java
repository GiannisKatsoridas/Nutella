package WebApplication.Model.Helpers;

import WebApplication.Implementation.Database.JPAResource;
import WebApplication.Model.Entities.CommentsEntity;
import WebApplication.Model.Entities.LikesEntity;
import WebApplication.Model.Entities.PostsEntity;
import WebApplication.Model.Entities.UsersEntity;

import javax.persistence.EntityManager;
import java.util.*;

public class PostsInterest {

    public static Map<Long, ArrayList<Integer>> values = new LinkedHashMap<Long, ArrayList<Integer>>();

    public static Map<Long, Integer> mapsPositions = new HashMap<Long, Integer>();

    public static void CreatePostsInterestMap(){

        EntityManager em = JPAResource.factory.createEntityManager();

        List<Long> users = em.createNamedQuery("UsersEntity.getAllUsersId").getResultList();
        List<PostsEntity> posts = em.createNamedQuery("PostsEntity.GetAllPosts").getResultList();
        List<LikesEntity> likes = em.createNamedQuery("LikesEntity.GetAllLikes").getResultList();
        List<CommentsEntity> comments = em.createNamedQuery("CommentsEntity.GetAllComments").getResultList();

        Map<Long, Map<Long, Integer>> userPostsMap = new LinkedHashMap<Long, Map<Long, Integer>>(users.size());

        for(Long u: users){
            userPostsMap.put(u, new LinkedHashMap<Long, Integer>(posts.size()));
        }

        for(LikesEntity l: likes){
            userPostsMap.get(l.getUserId()).put(l.getPostId(), 1);
        }

        for(CommentsEntity c: comments){
            if(!userPostsMap.get(c.getUserId()).containsKey(c.getPostId())){
                userPostsMap.get(c.getUserId()).put(c.getPostId(), 1);
            }
            else{
                userPostsMap.get(c.getUserId()).put(c.getPostId(), 2);
            }
        }

        boolean start = true;
        int it = 0;

        for(Long u: users){

            ArrayList<Integer> list = new ArrayList<Integer>();

            for(PostsEntity p: posts){

                if(start){                              // Creation of the map mapsPositions that shows for every job their position in the UsersApplication.values ArrayList
                    PostsInterest.mapsPositions.put(p.getId(), it);
                    it++;
                }

                if(userPostsMap.get(u).containsKey(p.getId())){
                    list.add(userPostsMap.get(u).get(p.getId()));
                }
                else{
                    list.add(0);
                }

            }

            start = false;

            PostsInterest.values.put(u, list);
        }

        PostsInterest.PrintUsersApplicationValues();
    }

    public static void PrintUsersApplicationValues(){

        EntityManager em = JPAResource.factory.createEntityManager();

        List<Long> users = em.createNamedQuery("UsersEntity.getAllUsersId").getResultList();

        for(Long u: users){

            List<Integer> list = PostsInterest.values.get(u);

            for(Integer i: list){

                System.out.print(i + " ");

            }
            System.out.println();
        }

        em.close();
    }
}
