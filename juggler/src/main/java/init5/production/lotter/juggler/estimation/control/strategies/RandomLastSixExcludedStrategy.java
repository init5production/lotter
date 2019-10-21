package init5.production.lotter.juggler.estimation.control.strategies;

import init5.production.lotter.juggler.estimation.control.estimators.BasicEstimator;
import init5.production.lotter.juggler.estimation.control.helpers.CollectionProvider;
import init5.production.lotter.juggler.estimation.entity.EstimationException;
import init5.production.lotter.juggler.estimation.entity.StrategyQualifier;
import init5.production.lotter.juggler.estimation.entity.StrategyType;

import javax.inject.Inject;

import static init5.production.lotter.juggler.crud.entity.Draw.NUMBERS_IN_DRAW;

/**
 * @author Jakub Barski
 */
@StrategyQualifier(StrategyType.RANDOM_LAST_SIX_EXCLUDED)
public class RandomLastSixExcludedStrategy implements Strategy {

    private static final int DRAWS_TO_ELIMINATE = 6;

    @Inject
    private CollectionProvider provider;

    @Inject
    private BasicEstimator estimator;

    @Override
    public int[] estimate() throws EstimationException {
        return estimator.estimate(
                provider.getNarrowed(DRAWS_TO_ELIMINATE),
                NUMBERS_IN_DRAW
        );
    }
}
