package example.service;

import org.springframework.stereotype.Component;
import example.domain.Entity;
import example.tx.TransactionHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Each call to a method of the service package's classes is wrapped in a transaction.
 * Transaction propagition is not handled, therefore calling a service method from a service method is not a good idea.
 */
@Component
public class DefaultSomeService implements SomeService {

    @Override
    public Entity saveEntity(String value) {
        Entity u = TransactionHolder.getDb().newInstance(Entity.class);
        u.setValue(value);
        return TransactionHolder.getDb().save(u);
    }

    @Override
    public List<Entity> getEntities() {
        ArrayList<Entity> ret = new ArrayList<Entity>();
        for (Entity entity : TransactionHolder.getDb().browseClass(Entity.class)) {
            ret.add(entity);
        }
        return ret;
    }
}
