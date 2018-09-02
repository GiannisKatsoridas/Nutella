import WebApplication.Implementation.Database.JPAResource;
import WebApplication.Implementation.Optimizations.JobsOptimizations;
import WebApplication.Implementation.Optimizations.KNN;
import WebApplication.Model.Entities.JobapplicationsEntity;
import WebApplication.Model.Entities.JobsEntity;
import WebApplication.Model.Helpers.PostsInterest;
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

        System.err.println("Creating the users applications map!");

        UserApplications.CreateUserApplicationsMap();

        System.err.println("Creating the posts interest map!");

        PostsInterest.CreatePostsInterestMap();

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
