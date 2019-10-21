package init5.production.lotter.juggler.estimation.control.helpers;

import init5.production.lotter.juggler.crud.boundary.NumberManager;
import init5.production.lotter.juggler.crud.entity.NumberGrouped;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jakub Barski
 */
public class CollectionProvider {

    @Inject
    private NumberManager numberManager;

    @Inject
    private Eliminator eliminator;

    @Inject
    private ToSubgroupDivider divider;

    public List<Integer> getNarrowed(int drawsToEliminate) {
        List<Integer> collection = numberManager
                .findAllGrouped()
                .stream()
                .map(NumberGrouped::getValue)
                .collect(Collectors.toList());

        return eliminator.discardNumbersFromLastDraws(collection, drawsToEliminate);
    }

    public List<Integer> getRarestNarrowed(long collectionSize, int drawsToEliminate) {
        List<Integer> collection = getCollection(collectionSize, Comparator.comparingLong(NumberGrouped::getHits));

        return eliminator.discardNumbersFromLastDraws(collection, drawsToEliminate);
    }

    public List<Integer> getMostCommonNarrowed(long collectionSize, int drawsToEliminate) {
        List<Integer> collection = getCollection(
                collectionSize,
                Comparator.comparingLong(NumberGrouped::getHits).reversed()
        );

        return eliminator.discardNumbersFromLastDraws(collection, drawsToEliminate);
    }

    public List<List<NumberGrouped>> getNarrowedInSubgroups(int drawsToEliminate) {
        List<NumberGrouped> narrowedCollection = eliminator.discardGroupedNumbersFromLastDraws(
                numberManager.findAllGrouped(),
                drawsToEliminate
        );

        return divider.divide(narrowedCollection);
    }

    private List<Integer> getCollection(long collectionSize, Comparator<NumberGrouped> comparator) {
        return numberManager.findAllGrouped()
                .stream()
                .sorted(comparator)
                .map(NumberGrouped::getValue)
                .limit(collectionSize)
                .collect(Collectors.toList());
    }
}
