package init5.production.lotter.juggler.estimation.control.estimators;

import init5.production.lotter.juggler.estimation.entity.EstimationException;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author Jakub Barski
 */
public class BasicEstimator {

    public int[] estimate(List<Integer> collection, int estimationSize) throws EstimationException {
        int collectionSize = collection.size();

        if (collectionSize < estimationSize) {
            throw new EstimationException("Given collection " + collection.toString() + " is too small to estimate " + estimationSize + " numbers.");
        }

        if (collectionSize == estimationSize) {
            return collection.stream().mapToInt(Integer::intValue).sorted().toArray();
        }

        Set<Integer> estimated = new HashSet<>();
        Random random = new Random();

        while (estimated.size() < estimationSize) {
            estimated.add(collection.remove(random.nextInt(collection.size())));
        }

        return estimated.stream().mapToInt(Integer::intValue).sorted().toArray();
    }
}
