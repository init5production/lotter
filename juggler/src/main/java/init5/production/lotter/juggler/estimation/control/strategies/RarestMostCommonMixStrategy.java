package init5.production.lotter.juggler.estimation.control.strategies;

import init5.production.lotter.juggler.estimation.control.estimators.BasicEstimator;
import init5.production.lotter.juggler.estimation.control.helpers.CollectionProvider;
import init5.production.lotter.juggler.estimation.entity.EstimationException;
import init5.production.lotter.juggler.estimation.entity.StrategyQualifier;
import init5.production.lotter.juggler.estimation.entity.StrategyType;
import org.apache.commons.lang3.tuple.ImmutablePair;

import javax.inject.Inject;
import javax.transaction.Transactional;
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

    @Transactional(Transactional.TxType.REQUIRED)
    @Override
    public ImmutablePair<StrategyType, int[]> estimate() throws EstimationException {
        List<Integer> rarest = provider.getRarestNarrowed(COLLECTION_SIZE, DRAWS_TO_ELIMINATE);
        List<Integer> mostCommon = provider.getMostCommonNarrowed(COLLECTION_SIZE, DRAWS_TO_ELIMINATE);

        int[] numbers = IntStream
                .concat(
                        IntStream.of(estimator.estimate(rarest, HALF_OF_NUMBERS_IN_DRAW)),
                        IntStream.of(estimator.estimate(mostCommon, HALF_OF_NUMBERS_IN_DRAW))
                )
                .sorted()
                .toArray();

        return ImmutablePair.of(StrategyType.RAREST_MOST_COMMON_MIXED, numbers);
    }
}
