package init5.production.lotter.juggler.estimation.control.strategies;

import init5.production.lotter.juggler.crud.entity.NumberGrouped;
import init5.production.lotter.juggler.estimation.control.estimators.SubgroupBasedEstimator;
import init5.production.lotter.juggler.estimation.control.helpers.CollectionProvider;
import init5.production.lotter.juggler.estimation.entity.EstimationException;
import init5.production.lotter.juggler.estimation.entity.StrategyQualifier;
import init5.production.lotter.juggler.estimation.entity.StrategyType;
import org.apache.commons.lang3.tuple.ImmutablePair;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Comparator;

/**
 * @author Jakub Barski
 */
@StrategyQualifier(StrategyType.RAREST_EQUAL_DISTRIBUTION)
public class RarestEqualDistributionStrategy implements Strategy {

    private static final int DRAWS_TO_ELIMINATE = 2;

    @Inject
    private CollectionProvider provider;

    @Inject
    private SubgroupBasedEstimator estimator;

    @Transactional(Transactional.TxType.REQUIRED)
    @Override
    public ImmutablePair<StrategyType, int[]> estimate() throws EstimationException {
        int[] numbers = estimator.estimate(
                provider.getNarrowedInSubgroups(DRAWS_TO_ELIMINATE),
                Comparator.comparingLong(NumberGrouped::getHits)
        );

        return ImmutablePair.of(StrategyType.RAREST_EQUAL_DISTRIBUTION, numbers);
    }
}
