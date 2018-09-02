package WebApplication.Implementation.Optimizations;

import WebApplication.Model.Helpers.PostsInterest;

public class PostsOptimizations {

    public static void AddPost(long postId){

        for(Long u: PostsInterest.values.keySet()){

            PostsInterest.values.get(u).add(0);

        }

        PostsInterest.mapsPositions.put(postId, PostsInterest.values.size()-1);

    }

    public static void PostReact(long userId, long postId){

        Integer pos = PostsInterest.mapsPositions.get(postId);

        Integer reacts = PostsInterest.values.get(userId).get(pos);

        if(reacts >= 2){
            return;
        }

        PostsInterest.values.get(userId).set(pos, reacts + 1);

    }
}
