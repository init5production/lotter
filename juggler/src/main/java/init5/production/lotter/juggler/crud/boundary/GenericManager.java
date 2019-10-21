package init5.production.lotter.juggler.crud.boundary;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Jakub Barski
 */
@Stateless
public class GenericManager {

    @PersistenceContext
    private EntityManager manager;

    public void persist(Object entity) {
        manager.persist(entity);
    }

    public void flushBatch() {
        manager.flush();
        manager.clear();
    }
}
