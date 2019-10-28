package init5.production.lotter.juggler.crud.entity;

import javax.persistence.*;

/**
 * @author Jakub Barski
 */
@Entity
public class Number {
    private static final String SEQUENCE = "NUMBER_ID_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    private Long id;

    private Integer value;

    @ManyToOne
    @JoinColumn(name = "DRAW_ID", nullable = false)
    private Draw draw;

    public Long getId() {
        return id;
    }

    public Number setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getValue() {
        return value;
    }

    public Number setValue(Integer value) {
        this.value = value;
        return this;
    }

    public Draw getDraw() {
        return draw;
    }

    public Number setDraw(Draw draw) {
        this.draw = draw;
        return this;
    }

    @Override
    public String toString() {
        return "Number{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
