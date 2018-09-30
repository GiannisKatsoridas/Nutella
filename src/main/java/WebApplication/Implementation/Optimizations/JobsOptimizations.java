package WebApplication.Implementation.Optimizations;

import WebApplication.Implementation.Database.JPAResource;
import WebApplication.Model.Helpers.UserApplications;
import org.apache.catalina.User;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class JobsOptimizations {

    public static void AddJob(long jobId){

        Long f = 0L;

        for(Long l: UserApplications.values.keySet()){

            UserApplications.values.get(l).add(0);

            f = l;
        }

        UserApplications.mapsPositions.put(jobId, UserApplications.values.get(f).size()-1);

    }

    public static void JobApplication(long userId, long jobId){

        Integer pos = UserApplications.mapsPositions.get(jobId);

        UserApplications.values.get(userId).set(pos, 1);

    }


    public static void AddUser(long id) {

        Integer length;

        length = UserApplications.values.get(1000000001L).size();        // The total jobs already posted

        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i=0; i<length; i++){
            list.add(0);
        }

        UserApplications.values.put(id, list);
    }
}
