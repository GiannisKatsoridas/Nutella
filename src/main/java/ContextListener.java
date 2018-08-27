import WebApplication.Implementation.Database.JPAResource;
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

        for(Long u: users){

            ArrayList<Integer> list = new ArrayList<Integer>();

            for(JobsEntity j: jobs){

                if(applicationsMap.get(u).containsKey(j.getId())) {
                    list.add(1);
                }
                else{
                    list.add(0);
                }

            }

            UserApplications.values.put(u, list);
        }



        for(Long u: users){

            List<Integer> list = UserApplications.values.get(u);

            for(Integer i: list){

                System.out.print(i + " ");

            }
            System.out.println();
        }

        for (Long u: users){

            System.out.println(KNN.CalculateDistance(UserApplications.values.get(u), UserApplications.values.get(1000000001L)));

        }

    }
}
