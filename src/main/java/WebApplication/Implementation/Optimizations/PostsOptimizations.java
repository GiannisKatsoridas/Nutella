package WebApplication.Implementation.Optimizations;

import WebApplication.Model.Helpers.PostsInterest;

import java.util.ArrayList;

public class PostsOptimizations {

    public static void AddPost(long postId){

        Long f = 0L;

        for(Long u: PostsInterest.values.keySet()){

            PostsInterest.values.get(u).add(0);

            f = u;
        }

        PostsInterest.mapsPositions.put(postId, PostsInterest.values.get(f).size()-1);

    }

    public static void PostReact(long userId, long postId){

        Integer pos = PostsInterest.mapsPositions.get(postId);

        Integer reacts = PostsInterest.values.get(userId).get(pos);

        if(reacts >= 2){
            return;
        }

        PostsInterest.values.get(userId).set(pos, reacts + 1);

    }

    public static void AddUser(long id) {

        Integer length;

        length = PostsInterest.values.get(1000000001L).size();        // The total posts already posted

        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i=0; i<length; i++){
            list.add(0);
        }

        PostsInterest.values.put(id, list);
    }
}
