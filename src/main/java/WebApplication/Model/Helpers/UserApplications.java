package WebApplication.Model.Helpers;

import WebApplication.Implementation.Database.JPAResource;

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
}
