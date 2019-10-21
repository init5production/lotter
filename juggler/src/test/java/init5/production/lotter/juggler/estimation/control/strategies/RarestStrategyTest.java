package init5.production.lotter.juggler.estimation.control.strategies;

import init5.production.lotter.juggler.estimation.control.CollectionProviderForTest;
import init5.production.lotter.juggler.estimation.control.estimators.BasicEstimator;
import init5.production.lotter.juggler.estimation.control.helpers.CollectionProvider;
import init5.production.lotter.juggler.estimation.entity.EstimationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Jakub Barski
 */
@ExtendWith(MockitoExtension.class)
class RarestStrategyTest {

    @Mock
    private CollectionProvider provider;

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private BasicEstimator estimator;

    @InjectMocks
    private RarestStrategy sut;

    @Test
    void estimate() throws EstimationException {
        List<Integer> collection = CollectionProviderForTest.getRarestNarrowed(6);
        when(provider.getRarestNarrowed(anyLong(), anyInt())).thenReturn(collection);

        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, sut.estimate());

        verify(provider).getRarestNarrowed(15, 2);
        verify(estimator).estimate(collection, 6);
    }

    @Test
    void estimateWithActualSizeCollection() throws EstimationException {
        List<Integer> collection = CollectionProviderForTest.getRarestNarrowed(15);
        when(provider.getRarestNarrowed(anyLong(), anyInt())).thenReturn(collection);

        int[] estimated = sut.estimate();

        assertTrue(IntStream.of(estimated).allMatch(i -> i <=  15));

        verify(provider).getRarestNarrowed(15, 2);
        verify(estimator).estimate(collection, 6);
    }
}