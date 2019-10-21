package init5.production.lotter.juggler.estimation.control.strategies;

import init5.production.lotter.juggler.crud.entity.NumberGrouped;
import init5.production.lotter.juggler.estimation.control.estimators.SubgroupBasedEstimator;
import init5.production.lotter.juggler.estimation.control.helpers.CollectionProvider;
import init5.production.lotter.juggler.estimation.control.strategies.Strategy;
import init5.production.lotter.juggler.estimation.entity.EstimationException;
import init5.production.lotter.juggler.estimation.entity.StrategyQualifier;
import init5.production.lotter.juggler.estimation.entity.StrategyType;

import javax.inject.Inject;
import java.util.Comparator;

/**
 * @author Jakub Barski
 */
@StrategyQualifier(StrategyType.MOST_COMMON_EQUAL_DISTRIBUTION)
public class MostCommonEqualDistributionStrategy implements Strategy {

    private static final int DRAWS_TO_ELIMINATE = 2;

    @Inject
    private CollectionProvider provider;

    @Inject
    private SubgroupBasedEstimator estimator;

    @Override
    public int[] estimate() throws EstimationException {
        return estimator.estimate(
                provider.getNarrowedInSubgroups(DRAWS_TO_ELIMINATE),
                Comparator.comparingLong(NumberGrouped::getHits).reversed()
        );
    }
}
