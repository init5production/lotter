package init5.production.lotter.juggler.estimation.control.helpers;

import init5.production.lotter.juggler.crud.entity.NumberGrouped;

import java.util.ArrayList;
import java.util.List;

import static init5.production.lotter.juggler.crud.entity.Draw.NUMBERS_IN_DRAW;

/**
 * @author Jakub Barski
 */
public class ToSubgroupDivider {

    List<List<NumberGrouped>> divide(List<NumberGrouped> collection) {
        List<List<NumberGrouped>> subgroups = prepareEmptySubgroups();

        for (NumberGrouped numberGrouped : collection) {
            Integer value = numberGrouped.getValue();

            int currentSubgroupNo = Double.valueOf(Math.ceil(value.doubleValue() / 9)).intValue();
            int currentSubgroupIndex = currentSubgroupNo - 1;

            subgroups.get(currentSubgroupIndex).add(numberGrouped);
        }

        return subgroups;
    }

    private List<List<NumberGrouped>> prepareEmptySubgroups() {
        List<List<NumberGrouped>> subgroups = new ArrayList<>();

        for (int i = 0; i < NUMBERS_IN_DRAW; i++) {
            subgroups.add(new ArrayList<NumberGrouped>());
        }

        return subgroups;
    }
}
