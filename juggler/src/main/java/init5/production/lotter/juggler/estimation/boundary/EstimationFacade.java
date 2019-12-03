package init5.production.lotter.juggler.estimation.boundary;

import init5.production.lotter.juggler.estimation.control.StrategyProvider;
import init5.production.lotter.juggler.estimation.entity.StrategyType;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @author Jakub Barski
 */
public class EstimationFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(EstimationFacade.class);

    @Inject
    private StrategyProvider strategyProvider;

    @Resource(lookup = "java:jboss/ee/concurrency/executor/default")
    private ExecutorService executorService;

    public StrategyType[] getStrategies() {
        return StrategyType.values();
    }

    public HashMap<StrategyType, int[]> estimate(StrategyType[] types) {
        HashMap<StrategyType, int[]> estimated = new HashMap<>();

        List.of(types)
                .stream()
                .map(type -> executorService.submit(() -> strategyProvider.provide(type).estimate()))
                .collect(Collectors.toList())
                .forEach(future -> safeGetFutureResult(future).ifPresent(
                        pair -> estimated.put(pair.getLeft(), pair.getRight()))
                );

        return estimated;
    }

    private Optional<ImmutablePair<StrategyType, int[]>> safeGetFutureResult(Future<ImmutablePair<StrategyType, int[]>> future) {
        try {
            return Optional.ofNullable(future.get());
        } catch (Exception e) {
            LOGGER.error("Could not estimate numbers!", e);
            return Optional.empty();
        }
    }
}
