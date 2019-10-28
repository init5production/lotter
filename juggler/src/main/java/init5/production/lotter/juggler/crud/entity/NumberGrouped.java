package init5.production.lotter.juggler.crud.entity;

/**
 * @author Jakub Barski
 */
public class NumberGrouped {

    private Integer value;

    private Long hits;

    public NumberGrouped(Integer value, Long hits) {
        this.value = value;
        this.hits = hits;
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
