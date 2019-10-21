package init5.production.lotter.juggler.estimation.control.helpers;

import init5.production.lotter.juggler.crud.boundary.DrawManager;
import init5.production.lotter.juggler.crud.entity.Draw;
import init5.production.lotter.juggler.crud.entity.Number;
import init5.production.lotter.juggler.crud.entity.NumberGrouped;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jakub Barski
 */
public class Eliminator {

    @Inject
    private DrawManager drawManager;

    List<Integer> discardNumbersFromLastDraws(List<Integer> collection, int drawNumber) {
        List<Integer> lastNumbers = getNumbersFromLastDraws(drawNumber);

        collection.removeAll(lastNumbers);

        return collection;
    }

    List<NumberGrouped> discardGroupedNumbersFromLastDraws(List<NumberGrouped> collection, int drawNumber) {
        List<Integer> lastNumbers = getNumbersFromLastDraws(drawNumber);

        return collection
                .stream()
                .filter(numberGrouped -> !lastNumbers.contains(numberGrouped.getValue()))
                .collect(Collectors.toList());
    }

    private List<Integer> getNumbersFromLastDraws(int drawNumber) {
        return drawManager.findLast(drawNumber)
                .stream()
                .map(Draw::getNumbers)
                .flatMap(Collection::stream)
                .map(Number::getValue)
                .collect(Collectors.toList());
    }
}
