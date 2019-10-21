package init5.production.lotter.juggler.estimation.control.helpers;

import init5.production.lotter.juggler.crud.boundary.NumberManager;
import init5.production.lotter.juggler.crud.entity.NumberGrouped;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * @author Jakub Barski
 */
@SuppressWarnings("unchecked")
@ExtendWith(MockitoExtension.class)
class CollectionProviderTest {

    private static final int DRAWS_TO_ELIMINATE = 1;
    private static final long COLLECTION_SIZE = 15;

    @Mock
    private NumberManager numberManager;

    @Mock
    private Eliminator eliminator;

    @Mock
    private ToSubgroupDivider divider;

    @InjectMocks
    private CollectionProvider sut;

    @BeforeEach
    void setUp() {
        when(numberManager.findAllGrouped()).thenReturn(
                IntStream.rangeClosed(1, 49)
                        .mapToObj(i -> NumberGrouped.valueOf(i, (long) i))
                        .collect(Collectors.toList())
        );
    }

    @Test
    void getNarrowed() {
        setEliminatorMock(integer -> integer < 11);

        List<Integer> narrowed = sut.getNarrowed(DRAWS_TO_ELIMINATE);

        assertEquals(10, narrowed.size());
        assertEquals(1, narrowed.get(0));
        assertEquals(10, narrowed.get(9));
    }

    @Test
    void getRarestNarrowed() {
        List<Integer> toEliminate = Arrays.asList(4, 6, 8);
        setEliminatorMock(integer -> !toEliminate.contains(integer));

        List<Integer> narrowed = sut.getRarestNarrowed(COLLECTION_SIZE, DRAWS_TO_ELIMINATE);

        assertArrayEquals(new Integer[]{1,2,3,5,7,9,10,11,12,13,14,15}, narrowed.toArray(Integer[]::new));
    }

    @Test
    void getMostCommonNarrowed() {
        List<Integer> toEliminate = Arrays.asList(36, 38, 40);
        setEliminatorMock(integer -> !toEliminate.contains(integer));

        List<Integer> narrowed = sut.getMostCommonNarrowed(COLLECTION_SIZE, DRAWS_TO_ELIMINATE);

        assertArrayEquals(new Integer[]{49,48,47,46,45,44,43,42,41,39,37,35}, narrowed.toArray(Integer[]::new));
    }

    @Test
    void getNarrowedInSubgroups() {
        when(divider.divide(any())).thenCallRealMethod();
        ArgumentCaptor<List<NumberGrouped>> captor = ArgumentCaptor.forClass(List.class);
        when(eliminator.discardGroupedNumbersFromLastDraws(captor.capture(), anyInt())).then(
                inv ->
                        captor.getValue()
                                .stream()
                                .filter(numberGrouped -> numberGrouped.getValue() < 9)
                                .collect(Collectors.toList())
        );

        List<List<NumberGrouped>> narrowed = sut.getNarrowedInSubgroups(DRAWS_TO_ELIMINATE);

        assertEquals(6, narrowed.size());
        assertEquals(1, narrowed.stream().filter(list -> !list.isEmpty()).count());
        assertArrayEquals(
                new Integer[]{1,2,3,4,5,6,7,8},
                narrowed.get(0).stream().map(NumberGrouped::getValue).toArray(Integer[]::new)
        );
    }

    private void setEliminatorMock(Predicate<Integer> predicate) {
        ArgumentCaptor<List<Integer>> captor = ArgumentCaptor.forClass(List.class);
        when(eliminator.discardNumbersFromLastDraws(captor.capture(), anyInt()))
                .then(inv -> captor.getValue().stream().filter(predicate).collect(Collectors.toList()));
    }
}