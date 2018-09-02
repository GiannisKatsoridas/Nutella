package WebApplication.Model.Helpers;

import WebApplication.Implementation.Database.JPAResource;
import WebApplication.Implementation.Optimizations.KNN;
import WebApplication.Model.Entities.JobapplicationsEntity;
import WebApplication.Model.Entities.JobsEntity;

import javax.persistence.EntityManager;
import java.util.*;

public class UserApplications {

    public static Map<Long, ArrayList<Integer>> values = new LinkedHashMap<Long, ArrayList<Integer>>();

    public static Map<Long, Integer> mapsPositions = new HashMap<Long, Integer>();


    public static void PrintUsersApplicationValues(){

        EntityManager em = JPAResource.factory.createEntityManager();

        List<Long> users = em.createNamedQuery("UsersEntity.getAllUsersId").getResultList();

        for(Long u: users){

            List<Integer> list = UserApplications.values.get(u);

            for(Integer i: list){

                System.out.print(i + " ");

            }
            System.out.println();
        }

        em.close();
    }

    public static void CreateUserApplicationsMap(){

        EntityManager em = JPAResource.factory.createEntityManager();

        List<Long> users = em.createNamedQuery("UsersEntity.getAllUsersId").getResultList();
        List<JobsEntity> jobs = em.createNamedQuery("JobsEntity.selectAllJobs").getResultList();
        List<JobapplicationsEntity> applications = em.createNamedQuery("JobapplicationsEntity.GetAllJobApplications").getResultList();

        Map<Long, Map<Long, Integer>> applicationsMap = new LinkedHashMap<Long, Map<Long, Integer>>(users.size());

        for(Long u: users){

            applicationsMap.put(u, new LinkedHashMap<Long, Integer>(jobs.size()));

        }

        for(JobapplicationsEntity j: applications){

            applicationsMap.get(j.getApplicant()).put(j.getJob(), 1);

        }

        boolean start = true;
        int it = 0;

        for(Long u: users){

            ArrayList<Integer> list = new ArrayList<Integer>();

            for(JobsEntity j: jobs){

                if(start){                              // Creation of the map mapsPositions that shows for every job their position in the UsersApplication.values ArrayList
                    UserApplications.mapsPositions.put(j.getId(), it);
                    it++;
                }

                if(applicationsMap.get(u).containsKey(j.getId())) {
                    list.add(1);
                }
                else{
                    list.add(0);
                }

            }
            start = false;

            UserApplications.values.put(u, list);
        }


        UserApplications.PrintUsersApplicationValues();

    }
}
