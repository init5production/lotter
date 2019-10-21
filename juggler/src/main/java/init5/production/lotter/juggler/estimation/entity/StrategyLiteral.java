package init5.production.lotter.juggler.estimation.entity;

import javax.enterprise.util.AnnotationLiteral;

/**
 * @author Jakub Barski
 */
public class StrategyLiteral extends AnnotationLiteral<StrategyQualifier> implements StrategyQualifier {

    private StrategyType type;

    public StrategyLiteral(StrategyType type) {
        this.type = type;
    }

    @Override
    public StrategyType value() {
        return type;
    }
}
