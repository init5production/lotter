package init5.production.lotter.juggler.estimation.control.estimators;

import init5.production.lotter.juggler.crud.entity.NumberGrouped;
import init5.production.lotter.juggler.estimation.entity.EstimationException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Jakub Barski
 */
public class SubgroupBasedEstimator {

    public int[] estimate(List<List<NumberGrouped>> subgroups, Comparator<NumberGrouped> comparator)
            throws EstimationException {
        List<NumberGrouped> estimated = new ArrayList<>();

        for (List<NumberGrouped> subgroup : subgroups) {
            estimated.add(
                    subgroup
                            .stream()
                            .min(comparator)
                            .orElseThrow(() -> new EstimationException("Could not obtain a single number in one of equally distributed subgroup!"))
            );
        }

        return estimated.stream().mapToInt(NumberGrouped::getValue).sorted().toArray();
    }
}