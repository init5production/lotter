package init5.production.lotter.juggler.crud.boundary;

import init5.production.lotter.juggler.crud.entity.NumberGrouped;
import init5.production.lotter.juggler.crud.entity.query.NumberGroupedSelect;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Jakub Barski
 */
@Stateless
public class NumberManager {

    @PersistenceContext
    private EntityManager manager;

    public List<NumberGrouped> findAllGrouped() {
        @SuppressWarnings("unchecked")
        List<Object[]> results = manager.createNamedQuery(NumberGroupedSelect.NAME).getResultList();

        return results.stream().map(NumberGrouped::valueOf).collect(Collectors.toList());
    }
}
