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

import static init5.production.lotter.juggler.crud.entity.Draw.NUMBERS_IN_DRAW;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
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

        assertArrayEquals(new int[]{44, 45, 46, 47, 48, 49}, sut.estimate());

        verify(provider).getMostCommonNarrowed(15, 2);
        verify(estimator).estimate(collection, 6);
    }

    @Test
    void estimateWithActualSizeCollection() throws EstimationException {
        List<Integer> collection = CollectionProviderForTest.getMostCommonNarrowed(15);
        when(provider.getMostCommonNarrowed(anyLong(), anyInt())).thenReturn(collection);

        int[] estimated = sut.estimate();

        assertTrue(IntStream.of(estimated).allMatch(i -> i >=  34));

        verify(provider).getMostCommonNarrowed(15, 2);
        verify(estimator).estimate(collection, 6);
    }
}