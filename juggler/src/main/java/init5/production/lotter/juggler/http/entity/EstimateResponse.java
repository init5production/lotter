package init5.production.lotter.juggler.http.entity;

import init5.production.lotter.juggler.estimation.entity.StrategyType;

import java.util.HashMap;

/**
 * @author Jakub Barski
 */
public class EstimateResponse {

    private HashMap<StrategyType, int[]> estimated;

    public HashMap<StrategyType, int[]> getEstimated() {
        return estimated;
    }

    public EstimateResponse setEstimated(HashMap<StrategyType, int[]> estimated) {
        this.estimated = estimated;
        return this;
    }

    public static class Result {
        private String strategy;
        private int[] numbers;

        public Result(String strategy, int[] numbers) {
            this.strategy = strategy;
            this.numbers = numbers;
        }

        public String getStrategy() {
            return strategy;
        }

        public int[] getNumbers() {
            return numbers;
        }
    }
}
