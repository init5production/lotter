package init5.production.lotter.juggler.estimation.control.helpers;

import init5.production.lotter.juggler.crud.entity.NumberGrouped;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jakub Barski
 */
class ToSubgroupDividerTest {

    private ToSubgroupDivider sut = new ToSubgroupDivider();

    @Test
    void divideIntoAllSubgroups() {
        List<NumberGrouped> toDivide = prepareToDivide(new int[]{1,10,24,30,39,49});

        List<List<NumberGrouped>> divided = sut.divide(toDivide);

        assertEquals(6, divided.size());
        assertEquals(6, divided.stream().filter(list -> !list.isEmpty()).count());
        assertEquals(1, divided.get(0).get(0).getValue());
        assertEquals(10, divided.get(1).get(0).getValue());
        assertEquals(24, divided.get(2).get(0).getValue());
        assertEquals(30, divided.get(3).get(0).getValue());
        assertEquals(39, divided.get(4).get(0).getValue());
        assertEquals(49, divided.get(5).get(0).getValue());
    }

    @Test
    void divideIntoOneSubgroup() {
        List<NumberGrouped> toDivide = prepareToDivide(new int[]{1,2,3,4,5,6});

        List<List<NumberGrouped>> divided = sut.divide(toDivide);

        assertEquals(6, divided.size());
        assertEquals(1, divided.stream().filter(list -> !list.isEmpty()).count());
        assertEquals(1, divided.get(0).get(0).getValue());
        assertEquals(2, divided.get(0).get(1).getValue());
        assertEquals(3, divided.get(0).get(2).getValue());
        assertEquals(4, divided.get(0).get(3).getValue());
        assertEquals(5, divided.get(0).get(4).getValue());
        assertEquals(6, divided.get(0).get(5).getValue());
    }

    private List<NumberGrouped> prepareToDivide(int[] numbers) {
        List<NumberGrouped> toDivide = new ArrayList<>();

        for(int number : numbers) {
            toDivide.add(NumberGrouped.valueOf(new Object[]{number, (long)number}));
        }

        return toDivide;
    }
}