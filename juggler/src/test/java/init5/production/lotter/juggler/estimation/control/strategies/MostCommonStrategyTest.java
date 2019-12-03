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
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Jakub Barski
 */
@ExtendWith(MockitoExtension.class)
class MostCommonStrategyTest {

    @Mock
    private CollectionProvider provider;

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private BasicEstimator estimator;

    @InjectMocks
    private MostCommonStrategy sut;

    @Test
    void estimate() throws EstimationException {
        List<Integer> collection = CollectionProviderForTest.getMostCommonNarrowed(6);
        when(provider.getMostCommonNarrowed(anyLong(), anyInt())).thenReturn(collection);

        ImmutablePair<StrategyType, int[]> estimated = sut.estimate();

        assertEquals(StrategyType.MOST_COMMON, estimated.getLeft());
        assertArrayEquals(new int[]{44, 45, 46, 47, 48, 49}, estimated.getRight());

        verify(provider).getMostCommonNarrowed(15, 2);
        verify(estimator).estimate(collection, 6);
    }

    @Test
    void estimateWithActualSizeCollection() throws EstimationException {
        List<Integer> collection = CollectionProviderForTest.getMostCommonNarrowed(15);
        when(provider.getMostCommonNarrowed(anyLong(), anyInt())).thenReturn(collection);

        ImmutablePair<StrategyType, int[]> estimated = sut.estimate();

        assertEquals(StrategyType.MOST_COMMON, estimated.getLeft());
        assertTrue(IntStream.of(estimated.getRight()).allMatch(i -> i >= 34));

        verify(provider).getMostCommonNarrowed(15, 2);
        verify(estimator).estimate(collection, 6);
    }
}