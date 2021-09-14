package co.com.poli.movies.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name="movies", schema="movies")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false,nullable = false,unique = true)
    private Long id;
    @NotEmpty(message = "El titulo no puede ser vacio")
    @Column(name="title")
    private String title;
    @NotEmpty(message = "El director no puede ser vacio")
    @Column(name="director")
    private String director;
    @Min(message = "El valor mínimo debe ser 1", value = 1)
    @Max(message = "El valor maximo es 5", value = 5)
    @Column(name="rating")
    private int rating;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
