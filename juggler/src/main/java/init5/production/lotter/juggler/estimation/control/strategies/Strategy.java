package init5.production.lotter.juggler.estimation.control.strategies;

import init5.production.lotter.juggler.estimation.entity.EstimationException;
import init5.production.lotter.juggler.estimation.entity.StrategyType;
import org.apache.commons.lang3.tuple.ImmutablePair;

/**
 * @author Jakub Barski
 */
public interface Strategy {

    ImmutablePair<StrategyType, int[]> estimate() throws EstimationException;
}
