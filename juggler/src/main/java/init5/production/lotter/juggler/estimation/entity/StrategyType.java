package init5.production.lotter.juggler.estimation.entity;

/**
 * @author Jakub Barski
 */
public enum StrategyType {
    RANDOM_LAST_SIX_EXCLUDED("Estimation from group created by exclusion numbers from last six draws."),
    RAREST("Estimation from group of rarely occurring numbers."),
    RANDOM_LAST_ONE_EXCLUDED("Random estimation but with excluded numbers from last draw."),
    MOST_COMMON("Estimation from group of most commonly occurring numbers."),
    RAREST_MOST_COMMON_MIXED("Estimation from a mixed group of rarely and most commonly occurring numbers."),
    RAREST_EQUAL_DISTRIBUTION("Estimation from rarely occurring numbers in subgroups."),
    MOST_COMMON_EQUAL_DISTRIBUTION("Estimation from most commonly occurring numbers in subgroups.");

    private String description;

    StrategyType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
