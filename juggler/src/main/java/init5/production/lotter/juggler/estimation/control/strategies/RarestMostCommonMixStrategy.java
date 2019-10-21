package init5.production.lotter.juggler.estimation.control.strategies;

import init5.production.lotter.juggler.estimation.control.estimators.BasicEstimator;
import init5.production.lotter.juggler.estimation.control.helpers.CollectionProvider;
import init5.production.lotter.juggler.estimation.entity.EstimationException;
import init5.production.lotter.juggler.estimation.entity.StrategyQualifier;
import init5.production.lotter.juggler.estimation.entity.StrategyType;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Jakub Barski
 */
@StrategyQualifier(StrategyType.RAREST_MOST_COMMON_MIXED)
public class RarestMostCommonMixStrategy implements Strategy {

    private static final int COLLECTION_SIZE = 7;
    private static final int DRAWS_TO_ELIMINATE = 2;
    private static final int HALF_OF_NUMBERS_IN_DRAW = 3;

    @Inject
    private CollectionProvider provider;

    @Inject
    private BasicEstimator estimator;

    @Override
    public int[] estimate() throws EstimationException {
        List<Integer> rarest = provider.getRarestNarrowed(COLLECTION_SIZE, DRAWS_TO_ELIMINATE);
        List<Integer> mostCommon = provider.getMostCommonNarrowed(COLLECTION_SIZE, DRAWS_TO_ELIMINATE);

        return IntStream
                .concat(
                        IntStream.of(estimator.estimate(rarest, HALF_OF_NUMBERS_IN_DRAW)),
                        IntStream.of(estimator.estimate(mostCommon, HALF_OF_NUMBERS_IN_DRAW))
                )
                .sorted()
                .toArray();
    }
}
