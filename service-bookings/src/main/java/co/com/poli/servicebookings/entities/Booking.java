package co.com.poli.servicebookings.entities;

import co.com.poli.servicebookings.model.Movie;
import co.com.poli.servicebookings.model.ShowTimes;
import co.com.poli.servicebookings.model.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name="bookings", schema="bookings")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false,nullable = false,unique = true)
    private Long id;
    @NotNull(message = "El user id no puede ser nulo")
    @Column(name="user_id")
    private Long userId;
    @Transient
    private User user;
    @NotNull(message = "El user id no puede ser nulo")
    @Column(name="showtime_id")
    private Long showTimeId;
    @Transient
    private ShowTimes showTimes;
    @Column(name="movies")
    private Long movies;
    private Movie movie;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
