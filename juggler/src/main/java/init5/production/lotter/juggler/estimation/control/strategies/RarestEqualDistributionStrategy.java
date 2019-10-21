package init5.production.lotter.juggler.estimation.control.strategies;

import init5.production.lotter.juggler.crud.boundary.NumberManager;
import init5.production.lotter.juggler.crud.entity.NumberGrouped;
import init5.production.lotter.juggler.estimation.control.estimators.SubgroupBasedEstimator;
import init5.production.lotter.juggler.estimation.control.helpers.CollectionProvider;
import init5.production.lotter.juggler.estimation.control.helpers.Eliminator;
import init5.production.lotter.juggler.estimation.control.helpers.ToSubgroupDivider;
import init5.production.lotter.juggler.estimation.control.strategies.Strategy;
import init5.production.lotter.juggler.estimation.entity.EstimationException;
import init5.production.lotter.juggler.estimation.entity.StrategyQualifier;
import init5.production.lotter.juggler.estimation.entity.StrategyType;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;

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

    @Override
    public int[] estimate() throws EstimationException {
        return estimator.estimate(
                provider.getNarrowedInSubgroups(DRAWS_TO_ELIMINATE),
                Comparator.comparingLong(NumberGrouped::getHits)
        );
    }
}
