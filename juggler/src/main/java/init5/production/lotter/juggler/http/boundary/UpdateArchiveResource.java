package init5.production.lotter.juggler.http.boundary;

import init5.production.lotter.juggler.archive.boundary.ArchiveFacade;
import init5.production.lotter.juggler.archive.entity.ArchiveUpdateException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * @author Jakub Barski
 */
@Stateless
@Path("update-archive")
@Produces("text/plain")
public class UpdateArchiveResource {

    @Inject
    private ArchiveFacade archiveFacade;

    @POST
    /*
     *  TODO: This method should provide more detailed feedback in a shape of json response
     */
    public Response update() {
        try {
            archiveFacade.downloadAndSave();
            return Response.ok().build();
        } catch (ArchiveUpdateException e) {
            return Response.status(422).build();
        }

    }
}
