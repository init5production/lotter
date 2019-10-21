package init5.production.lotter.juggler.estimation.control.helpers;

import init5.production.lotter.juggler.crud.boundary.DrawManager;
import init5.production.lotter.juggler.crud.entity.Draw;
import init5.production.lotter.juggler.crud.entity.Number;
import init5.production.lotter.juggler.crud.entity.NumberGrouped;
import init5.production.lotter.juggler.estimation.control.helpers.Eliminator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EliminatorTest {

    @Mock
    private DrawManager drawManager;

    @InjectMocks
    private Eliminator sut;

    @BeforeEach
    void setUp() {
        Draw draw = new Draw().setNumbers(Arrays.asList(
                new Number().setValue(2),
                new Number().setValue(4))
        );

        when(drawManager.findLast(anyInt())).thenReturn(Collections.singletonList(draw));
    }

    @Test
    void discardNumbersFromLastDraws() {
        List<Integer> collection = new ArrayList<>();
        collection.add(1);
        collection.add(2);
        collection.add(3);
        collection.add(4);

        List<Integer> processedCollection = sut.discardNumbersFromLastDraws(collection, 1);

        assertEquals(Arrays.asList(1,3), processedCollection);
    }

    @Test
    void discardGroupedNumbersFromLastDraws() {
        List<NumberGrouped> collection = new ArrayList<>();
        collection.add(NumberGrouped.valueOf(1, 1L));
        collection.add(NumberGrouped.valueOf(2, 1L));
        collection.add(NumberGrouped.valueOf(3, 1L));
        collection.add(NumberGrouped.valueOf(4, 1L));

        List<NumberGrouped> narrowedCollection = sut.discardGroupedNumbersFromLastDraws(collection, 1);

        assertEquals(
                Arrays.asList(1,3),
                narrowedCollection.stream().map(NumberGrouped::getValue).collect(Collectors.toList())
        );
    }
}