package init5.production.lotter.juggler.estimation.control;

import init5.production.lotter.juggler.estimation.control.strategies.Strategy;
import init5.production.lotter.juggler.estimation.entity.EstimationException;
import init5.production.lotter.juggler.estimation.entity.StrategyLiteral;
import init5.production.lotter.juggler.estimation.entity.StrategyType;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

/**
 * @author Jakub Barski
 */
public class StrategyProvider {

    @Inject
    @Any
    private Instance<Strategy> strategyInstance;

    public Strategy provide(StrategyType type) throws EstimationException {
        StrategyLiteral strategyLiteral = new StrategyLiteral(type);
        Instance<Strategy> selectedStrategyInstance = strategyInstance.select(strategyLiteral);

        if (!selectedStrategyInstance.isResolvable()) {
            throw new EstimationException("Could not instantiate strategy of type [" + type + "]!");
        }


        return selectedStrategyInstance.get();
    }
}
