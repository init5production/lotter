package init5.production.lotter.juggler.crud.entity.query;

/**
 * @author Jakub Barski
 */
public class NumberGroupedSelect {
    public static final String NAME = "NumberGrouped.select";
    public static final String QUERY = "SELECT n.value, count(n.id) AS hits FROM Number n GROUP BY n.value";
}
