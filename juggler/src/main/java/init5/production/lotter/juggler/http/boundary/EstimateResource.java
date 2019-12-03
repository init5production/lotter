package init5.production.lotter.juggler.http.boundary;

import init5.production.lotter.juggler.estimation.boundary.EstimationFacade;
import init5.production.lotter.juggler.estimation.entity.StrategyType;
import init5.production.lotter.juggler.http.entity.EstimateRequest;
import init5.production.lotter.juggler.http.entity.EstimateResponse;
import init5.production.lotter.juggler.http.entity.EstimationStrategiesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jakub Barski
 */
@Stateless
@Path("estimate")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EstimateResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(EstimateResource.class);

    @Inject
    private EstimationFacade estimationFacade;

    @GET
    @Path("strategies")
    public EstimationStrategiesResponse estimationStrategies() {
        List<EstimationStrategiesResponse.Strategy> strategies = Arrays.stream(estimationFacade.getStrategies())
                .map(item -> EstimationStrategiesResponse.Strategy.valueOf(item.name(), item.getDescription()))
                .collect(Collectors.toList());

        return new EstimationStrategiesResponse().setStrategies(strategies);
    }

    @POST
    public Response estimate(@Valid @NotNull EstimateRequest request) {
        HashMap<StrategyType, int[]> estimated = estimationFacade.estimate(request.getStrategies());

        return Response.ok(new EstimateResponse().setEstimated(estimated)).build();
    }
}
