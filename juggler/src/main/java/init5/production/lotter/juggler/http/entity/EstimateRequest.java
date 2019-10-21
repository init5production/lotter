package init5.production.lotter.juggler.http.entity;

import init5.production.lotter.juggler.estimation.entity.StrategyType;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 * @author Jakub Barski
 */
public class EstimateRequest {

    @NotNull
    private StrategyType[] strategies;

    public StrategyType[] getStrategies() {
        return strategies;
    }

    public void setStrategies(StrategyType[] strategies) {
        this.strategies = strategies;
    }

    @Override
    public String toString() {
        return "EstimateRequest{" +
                "strategies=" + Arrays.toString(strategies) +
                '}';
    }
}
