package init5.production.lotter.juggler.http.entity;

import java.util.List;

/**
 * @author Jakub Barski
 */
public class EstimationStrategiesResponse {

    private List<Strategy> strategies;

    public List<Strategy> getStrategies() {
        return strategies;
    }

    public EstimationStrategiesResponse setStrategies(List<Strategy> strategies) {
        this.strategies = strategies;
        return this;
    }

    public static class Strategy {
        private String name;
        private String description;

        public static Strategy valueOf(String name, String description) {
            Strategy strategy = new Strategy();
            strategy.name = name;
            strategy.description = description;
            return strategy;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }
    }
}
