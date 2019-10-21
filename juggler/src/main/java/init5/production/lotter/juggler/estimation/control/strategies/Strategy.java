package init5.production.lotter.juggler.estimation.control.strategies;

import init5.production.lotter.juggler.estimation.entity.EstimationException;

/**
 * @author Jakub Barski
 */
public interface Strategy {

    int[] estimate() throws EstimationException;
}
