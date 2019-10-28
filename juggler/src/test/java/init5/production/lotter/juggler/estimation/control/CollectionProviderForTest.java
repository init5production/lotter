package init5.production.lotter.juggler.estimation.control;

import init5.production.lotter.juggler.crud.entity.NumberGrouped;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Jakub Barski
 */
public class CollectionProviderForTest {

    public static List<Integer> getMostCommonNarrowed(long collectionSize) {
        return IntStream.rangeClosed(1, 49)
                .boxed()
                .sorted(Comparator.comparingInt(Integer::intValue).reversed())
                .limit(collectionSize)
                .collect(Collectors.toList());
    }

    public static List<Integer> getRarestNarrowed(long collectionSize) {
        return IntStream.rangeClosed(1, 49)
                .boxed()
                .limit(collectionSize)
                .collect(Collectors.toList());
    }

    public static List<List<NumberGrouped>> getNarrowedInSubgroups() {
        ArrayList<List<NumberGrouped>> collection = new ArrayList<>();

        for (int i = 1; i <= 6; i++) {
            int max = 8 * i;
            collection.add(
                    IntStream.rangeClosed(max - 7, max)
                            .mapToObj(j -> new NumberGrouped(j, (long) j))
                            .collect(Collectors.toList())
            );
        }

        return collection;
    }
}
