package init5.production.lotter.juggler.crud.entity;

/**
 * @author Jakub Barski
 */
public class NumberGrouped {

    private Integer value;

    private Long hits;

    public static NumberGrouped valueOf(Object[] init) {
        if (!(init[0] instanceof Integer)) {
            throw new IllegalArgumentException("First item of init array has to be an Integer!");
        }

        if (!(init[1] instanceof Long)) {
            throw new IllegalArgumentException("Second item of init array has to be a Long!");
        }

        return NumberGrouped.valueOf((Integer) init[0], (Long) init[1]);
    }

    public static NumberGrouped valueOf(int number, long hits) {
        return new NumberGrouped().setValue(number).setHits(hits);
    }

    public Integer getValue() {
        return value;
    }

    public NumberGrouped setValue(Integer value) {
        this.value = value;
        return this;
    }

    public Long getHits() {
        return hits;
    }

    public NumberGrouped setHits(Long hits) {
        this.hits = hits;
        return this;
    }

    @Override
    public String toString() {
        return "NumberGrouped{" +
                "value=" + value +
                ", hits=" + hits +
                '}';
    }
}
