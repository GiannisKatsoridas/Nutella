package WebApplication.Implementation.Optimizations;

import WebApplication.Implementation.Database.JPAResource;
import WebApplication.Model.Helpers.UserApplications;

import javax.persistence.EntityManager;
import java.util.List;

public class JobsOptimizations {

    public static void AddJob(long jobId){

        for(Long l: UserApplications.values.keySet()){

            UserApplications.values.get(l).add(0);

        }

        UserApplications.mapsPositions.put(jobId, UserApplications.values.size()-1);

    }

    public static void JobApplication(long userId, long jobId){

        Integer pos = UserApplications.mapsPositions.get(jobId);

        UserApplications.values.get(userId).set(pos, 1);

    }


}
