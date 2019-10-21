package init5.production.lotter.juggler.estimation.control.strategies;

import init5.production.lotter.juggler.crud.entity.NumberGrouped;
import init5.production.lotter.juggler.estimation.control.CollectionProviderForTest;
import init5.production.lotter.juggler.estimation.control.estimators.SubgroupBasedEstimator;
import init5.production.lotter.juggler.estimation.control.helpers.CollectionProvider;
import init5.production.lotter.juggler.estimation.entity.EstimationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Comparator;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Jakub Barski
 */
@ExtendWith(MockitoExtension.class)
class MostCommonEqualDistributionStrategyTest {

    @Mock
    private CollectionProvider provider;

    @Mock
    private SubgroupBasedEstimator estimator;

    @InjectMocks
    private MostCommonEqualDistributionStrategy sut;

    @Test
    void estimate() throws EstimationException {
        List<List<NumberGrouped>> collection = CollectionProviderForTest.getNarrowedInSubgroups();
        when(provider.getNarrowedInSubgroups(anyInt())).thenReturn(collection);

        sut.estimate();

        verify(provider).getNarrowedInSubgroups(2);
        //noinspection unchecked
        verify(estimator).estimate(eq(collection), any(Comparator.class));
    }
}