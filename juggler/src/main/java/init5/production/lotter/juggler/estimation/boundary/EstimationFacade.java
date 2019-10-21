package init5.production.lotter.juggler.estimation.boundary;

import init5.production.lotter.juggler.estimation.control.StrategyProvider;
import init5.production.lotter.juggler.estimation.entity.EstimationException;
import init5.production.lotter.juggler.estimation.entity.StrategyType;

import javax.inject.Inject;
import java.util.HashMap;

/**
 * @author Jakub Barski
 */
public class EstimationFacade {

    @Inject
    private StrategyProvider strategyProvider;

    public StrategyType[] getStrategies() {
        return StrategyType.values();
    }

    public HashMap<StrategyType, int[]> estimate(StrategyType[] types) throws EstimationException {
        HashMap<StrategyType, int[]> estimated = new HashMap<>();

        for (StrategyType type : types) {
            estimated.put(type, strategyProvider.provide(type).estimate());
        }

        return estimated;
    }
}
