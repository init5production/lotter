package init5.production.lotter.juggler.crud.boundary;

import init5.production.lotter.juggler.crud.entity.Draw;
import init5.production.lotter.juggler.crud.entity.query.DrawFindLast;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * @author Jakub Barski
 */
@Stateless
public class DrawManager {

    @PersistenceContext
    private EntityManager manager;

    public Optional<Draw> findLast() {
        List<Draw> list = findLast(1);
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    public List<Draw> findLast(int number) {
        return manager.createNamedQuery(DrawFindLast.NAME, Draw.class).setMaxResults(number).getResultList();
    }
}
