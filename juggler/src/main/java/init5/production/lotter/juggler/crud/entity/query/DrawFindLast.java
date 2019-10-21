package init5.production.lotter.juggler.crud.entity.query;

/**
 * @author Jakub Barski
 */
public class DrawFindLast {
    public static final String NAME = "Draw.findLast";
    public static final String QUERY = "SELECT d FROM Draw d ORDER BY d.id DESC";
}
