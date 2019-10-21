package init5.production.lotter.juggler.estimation.control.strategies;

import init5.production.lotter.juggler.estimation.control.CollectionProviderForTest;
import init5.production.lotter.juggler.estimation.control.estimators.BasicEstimator;
import init5.production.lotter.juggler.estimation.control.helpers.CollectionProvider;
import init5.production.lotter.juggler.estimation.entity.EstimationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Jakub Barski
 */
@ExtendWith(MockitoExtension.class)
class RandomLastSixExcludedStrategyTest {

    @Mock
    private CollectionProvider provider;

    @Mock
    private BasicEstimator estimator;

    @InjectMocks
    private RandomLastSixExcludedStrategy sut;

    @Test
    void estimate() throws EstimationException {
        List<Integer> collection = CollectionProviderForTest.getRarestNarrowed(6);
        when(provider.getNarrowed(anyInt())).thenReturn(collection);

        sut.estimate();

        verify(provider).getNarrowed(6);
        verify(estimator).estimate(collection, 6);
    }
}