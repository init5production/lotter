package init5.production.lotter.juggler.estimation.control.strategies;

import init5.production.lotter.juggler.crud.boundary.NumberManager;
import init5.production.lotter.juggler.crud.entity.NumberGrouped;
import init5.production.lotter.juggler.estimation.control.helpers.CollectionProvider;
import init5.production.lotter.juggler.estimation.control.helpers.Eliminator;
import init5.production.lotter.juggler.estimation.control.estimators.BasicEstimator;
import init5.production.lotter.juggler.estimation.control.strategies.Strategy;
import init5.production.lotter.juggler.estimation.entity.EstimationException;
import init5.production.lotter.juggler.estimation.entity.StrategyQualifier;
import init5.production.lotter.juggler.estimation.entity.StrategyType;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static init5.production.lotter.juggler.crud.entity.Draw.NUMBERS_IN_DRAW;

/**
 * @author Jakub Barski
 */
@StrategyQualifier(StrategyType.RAREST)
public class RarestStrategy implements Strategy  {

    private static final long COLLECTION_SIZE = 15L;
    private static final int DRAWS_TO_ELIMINATE = 2;

    @Inject
    private CollectionProvider provider;

    @Inject
    private BasicEstimator estimator;

    @Override
    public int[] estimate() throws EstimationException {
        return estimator.estimate(
                provider.getRarestNarrowed(COLLECTION_SIZE, DRAWS_TO_ELIMINATE),
                NUMBERS_IN_DRAW
        );
    }
}
