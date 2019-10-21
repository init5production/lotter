package init5.production.lotter.juggler.crud.entity;

import init5.production.lotter.juggler.crud.entity.query.DrawFindLast;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Jakub Barski
 */
@NamedQuery(name = DrawFindLast.NAME, query = DrawFindLast.QUERY)
@Entity
public class Draw {

    public static final int NUMBERS_IN_DRAW = 6;

    @Id
    private Long id;

    @Column(name = "DRAW_DATE")
    private LocalDate date;

    @OneToMany(mappedBy = "draw")
    private List<Number> numbers;

    public Long getId() {
        return id;
    }

    public Draw setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public Draw setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public List<Number> getNumbers() {
        return numbers;
    }

    public Draw setNumbers(List<Number> numbers) {
        this.numbers = numbers;
        return this;
    }

    @Override
    public String toString() {
        return "Draw{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}
