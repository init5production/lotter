package init5.production.lotter.juggler.estimation.control.strategies;

import init5.production.lotter.juggler.estimation.control.CollectionProviderForTest;
import init5.production.lotter.juggler.estimation.control.estimators.BasicEstimator;
import init5.production.lotter.juggler.estimation.control.helpers.CollectionProvider;
import init5.production.lotter.juggler.estimation.entity.EstimationException;
import init5.production.lotter.juggler.estimation.entity.StrategyType;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Jakub Barski
 */
@ExtendWith(MockitoExtension.class)
class RarestMostCommonMixStrategyTest {

    @Mock
    private CollectionProvider provider;

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private BasicEstimator estimator;

    @InjectMocks
    private RarestMostCommonMixStrategy sut;

    @Test
    void estimate() throws EstimationException {
        List<Integer> rarestCollection = CollectionProviderForTest.getRarestNarrowed(3);
        List<Integer> mostCommonCollection = CollectionProviderForTest.getMostCommonNarrowed(3);

        when(provider.getRarestNarrowed(anyLong(), anyInt())).thenReturn(rarestCollection);
        when(provider.getMostCommonNarrowed(anyLong(), anyInt())).thenReturn(mostCommonCollection);


        ImmutablePair<StrategyType, int[]> estimated = sut.estimate();

        assertEquals(StrategyType.RAREST_MOST_COMMON_MIXED, estimated.getLeft());
        assertArrayEquals(new int[]{1, 2, 3, 47, 48, 49}, estimated.getRight());

        verify(provider).getRarestNarrowed(7, 2);
        verify(provider).getMostCommonNarrowed(7, 2);
        verify(estimator).estimate(rarestCollection, 3);
        verify(estimator).estimate(mostCommonCollection, 3);
    }
}