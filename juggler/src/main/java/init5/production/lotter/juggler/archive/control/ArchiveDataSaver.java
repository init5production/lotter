package init5.production.lotter.juggler.archive.control;

import init5.production.lotter.juggler.crud.boundary.DrawManager;
import init5.production.lotter.juggler.crud.boundary.GenericManager;
import init5.production.lotter.juggler.crud.entity.Draw;
import init5.production.lotter.juggler.crud.entity.Number;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Jakub Barski
 */
public class ArchiveDataSaver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArchiveDataSaver.class);

    private static final String DATE_FORMAT = "dd.MM.yyyy";

    @Inject
    private GenericManager genericManager;

    @Inject
    private DrawManager drawManager;

    public int convertAndSave(String rawData) {
        Optional<Draw> lastDraw = drawManager.findLast();
        long lastId = lastDraw.isPresent() ? lastDraw.get().getId() : 0L;

        AtomicInteger counter = new AtomicInteger();

        rawData.lines().forEach(line -> processSingleLine(line, lastId, counter));

        return counter.get();
    }

    private void processSingleLine(String line, long lastId, AtomicInteger counter) {
        // Example line:
        // 1. 27.01.1957 8,12,31,39,43,45

        String[] parts = line.split("\\s+");
        Long id = Long.valueOf(parts[0].replace(".", ""));

        if (id <= lastId) return;

        Draw draw = new Draw()
                .setId(id)
                .setDate(LocalDate.parse(parts[1], DateTimeFormatter.ofPattern(DATE_FORMAT)));

        genericManager.persist(draw);

        LOGGER.info("Persisted Draw #{}.", draw.getId());

        String[] numbers = parts[2].split(",");

        for (String numberAsString : numbers) {
            Number number = new Number().setDraw(draw).setValue(Integer.valueOf(numberAsString));
            genericManager.persist(number);
        }

        if (counter.getAndIncrement() % 7 == 0) {
            LOGGER.info("Flushing prepared batch.");
            genericManager.flushBatch();
        }
    }
}
