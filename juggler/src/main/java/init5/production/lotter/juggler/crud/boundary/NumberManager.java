package init5.production.lotter.juggler.crud.boundary;

import init5.production.lotter.juggler.crud.entity.NumberGrouped;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Jakub Barski
 */
@Stateless
public class NumberManager {

    @PersistenceContext
    private EntityManager manager;

    public List<NumberGrouped> findAllGrouped() {
        return manager.createQuery(
                "SELECT new init5.production.lotter.juggler.crud.entity.NumberGrouped(" +
                        "n.value, " +
                        "count(n.id)" +
                        ") FROM Number n GROUP BY n.value",
                NumberGrouped.class
        ).getResultList();
    }
}
