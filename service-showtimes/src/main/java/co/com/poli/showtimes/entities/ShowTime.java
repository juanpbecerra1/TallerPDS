package co.com.poli.showtimes.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name="show_times", schema="showtimes")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false,nullable = false,unique = true)
    private Long id;
    @NotNull(message = "La fecha no puede ser null")
    @Column(name="date_movie")
    private Date dateMovie;
    @Column(name="movies")
    private int movies;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShowTime showTime = (ShowTime) o;
        return Objects.equals(id, showTime.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
