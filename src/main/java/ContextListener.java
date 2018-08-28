import WebApplication.Implementation.Database.JPAResource;
import WebApplication.Implementation.Optimizations.JobsOptimizations;
import WebApplication.Implementation.Optimizations.KNN;
import WebApplication.Model.Entities.JobapplicationsEntity;
import WebApplication.Model.Entities.JobsEntity;
import WebApplication.Model.Helpers.UserApplications;
import WebApplication.Model.Entities.UsersEntity;

import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.*;

public class ContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {

        System.out.println("Application has begun :-)");

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

        for (Long u: users){

            System.out.println(KNN.CalculateDistance(UserApplications.values.get(u), UserApplications.values.get(1000000001L)));

        }

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
