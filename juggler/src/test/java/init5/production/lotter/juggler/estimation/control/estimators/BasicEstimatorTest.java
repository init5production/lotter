package init5.production.lotter.juggler.estimation.control.estimators;

import init5.production.lotter.juggler.estimation.entity.EstimationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jakub Barski
 */
class BasicEstimatorTest {

    private BasicEstimator sut = new BasicEstimator();
    private List<Integer> collection = new ArrayList<>();

    @BeforeEach
    void setUp() {
        IntStream.rangeClosed(1,6).forEach(collection::add);
    }

    @Test
    void estimateExceptionThrownDueToCollectionToSmall() throws EstimationException {
        assertThrows(EstimationException.class, () -> sut.estimate(collection, 7));
    }

    @Test
    void estimateCollectionJustInSize() throws EstimationException {
        int[] estimated = sut.estimate(collection, 6);

        assertArrayEquals(new int[]{1,2,3,4,5,6}, estimated);
    }

    @Test
    void estimate() throws EstimationException {
        int[] estimated = sut.estimate(collection, 3);

        assertEquals(3, estimated.length);
        collection.containsAll(Arrays.asList(estimated));
    }
}