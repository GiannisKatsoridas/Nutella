package WebApplication.Implementation.Database;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAResource {

    public static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("DbPersistenceUnit");

}
