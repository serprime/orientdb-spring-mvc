package example.tx;

import com.orientechnologies.orient.core.exception.OStorageException;
import com.orientechnologies.orient.core.tx.OTransaction;
import com.orientechnologies.orient.object.db.OObjectDatabasePool;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Aspect
public class TxAdvice {

    /**
     * Check id the database is available,
     * create it if not.
     * Register domain model classes.
     */
    @PostConstruct
    public void initializeDatabase() {
        OObjectDatabaseTx db = null;
        try {
            try {
                db = OObjectDatabasePool.global().acquire("local:database", "admin", "admin");
            } catch (OStorageException e) {
                new OObjectDatabaseTx("local:database").create();
                db = OObjectDatabasePool.global().acquire("local:database", "admin", "admin");
            }
            db.getEntityManager().registerEntityClasses("example.domain");
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    /**
     * Advice to wrap transactional methods.
     * We declare all methods of the service package to be wrapped in a transaction.
     */
    @Around("within (example.service..*)")
    public Object test(ProceedingJoinPoint pjp) throws Throwable {
        OObjectDatabaseTx db = OObjectDatabasePool.global().acquire("local:database", "admin", "admin");
        try {
            db.begin(OTransaction.TXTYPE.OPTIMISTIC);
            TransactionHolder.setDb(db);
            Object ret = pjp.proceed();
            db.commit();
            return ret;
        } catch (Exception e) {
            db.rollback();
            throw new RuntimeException("Could not commit transaction.", e);
        } finally {
            TransactionHolder.remove();
            db.close();
        }

    }

    /**
     * Close the database before shutdown of the application.
     */
    @PreDestroy
    public void close() {
        OObjectDatabasePool.global().close();
    }

}



