package init5.production.lotter.juggler.archive.boundary;

import init5.production.lotter.juggler.archive.control.ArchiveConnector;
import init5.production.lotter.juggler.archive.control.ArchiveDataSaver;
import init5.production.lotter.juggler.archive.entity.ArchiveUpdateException;

import javax.inject.Inject;

/**
 * @author Jakub Barski
 */
public class ArchiveFacade {

    @Inject
    private ArchiveConnector connector;

    @Inject
    private ArchiveDataSaver saver;

    public int downloadAndSave() throws ArchiveUpdateException {
        String rawData = connector.getRawData();
        if (rawData.isEmpty()) {
            throw new ArchiveUpdateException("Could not obtain file from remote server.");
        }

        return saver.convertAndSave(rawData);
    }
}
