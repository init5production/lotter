package init5.production.lotter.juggler.http.boundary;

import init5.production.lotter.juggler.crud.boundary.DrawManager;
import init5.production.lotter.juggler.crud.entity.Draw;
import init5.production.lotter.juggler.http.entity.HealthCheckResponse;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

/**
 * @author Jakub Barski
 */
@Stateless
@Path("health-check")
@Produces(MediaType.APPLICATION_JSON)
public class HealthCheckResource {

    @Inject
    private DrawManager drawManager;

    @GET
    public Response check() {
        Optional<Draw> draw = drawManager.findLast();
        HealthCheckResponse healthCheckResponse = draw.isPresent()
                ? new HealthCheckResponse()
                .setStatus(HealthCheckResponse.Status.OK)
                .setDateOfLastDraw(draw.get().getDate().toString())
                : new HealthCheckResponse()
                .setStatus(HealthCheckResponse.Status.ERROR)
                .setError("Draw archive is empty!");

        return Response.ok(healthCheckResponse).build();
    }
}