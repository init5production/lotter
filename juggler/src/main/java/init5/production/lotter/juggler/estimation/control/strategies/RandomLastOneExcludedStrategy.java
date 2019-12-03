package init5.production.lotter.juggler.estimation.control.strategies;

import init5.production.lotter.juggler.estimation.control.estimators.BasicEstimator;
import init5.production.lotter.juggler.estimation.control.helpers.CollectionProvider;
import init5.production.lotter.juggler.estimation.entity.EstimationException;
import init5.production.lotter.juggler.estimation.entity.StrategyQualifier;
import init5.production.lotter.juggler.estimation.entity.StrategyType;
import org.apache.commons.lang3.tuple.ImmutablePair;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static init5.production.lotter.juggler.crud.entity.Draw.NUMBERS_IN_DRAW;

/**
 * @author Jakub Barski
 */
@StrategyQualifier(StrategyType.RANDOM_LAST_ONE_EXCLUDED)
public class RandomLastOneExcludedStrategy implements Strategy {

    private static final int DRAWS_TO_ELIMINATE = 1;

    @Inject
    private CollectionProvider provider;

    @Inject
    private BasicEstimator estimator;

    @Transactional(Transactional.TxType.REQUIRED)
    @Override
    public ImmutablePair<StrategyType, int[]> estimate() throws EstimationException {
        int[] numbers = estimator.estimate(
                provider.getNarrowed(DRAWS_TO_ELIMINATE),
                NUMBERS_IN_DRAW
        );

        return ImmutablePair.of(StrategyType.RANDOM_LAST_ONE_EXCLUDED, numbers);
    }
}
