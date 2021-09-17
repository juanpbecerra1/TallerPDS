package co.com.poli.servicebookings.services;

import co.com.poli.servicebookings.client.MovieClient;
import co.com.poli.servicebookings.client.ShowTimesClient;
import co.com.poli.servicebookings.client.UserClient;
import co.com.poli.servicebookings.entities.Booking;
import co.com.poli.servicebookings.model.Movie;
import co.com.poli.servicebookings.model.ShowTimes;
import co.com.poli.servicebookings.model.User;
import co.com.poli.servicebookings.repositories.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServicesImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final MovieClient movieClient;
    private final ShowTimesClient showTimesClient;
    private final UserClient userClient;

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public void save(Booking booking) {
            bookingRepository.save(booking);
    }

    @Override
    public Booking findById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Booking booking) {
        bookingRepository.delete(booking);
    }

    @Override
    public Booking findByUserId(Long userId) {
        Booking booking = bookingRepository.findByUserId(userId);
        ModelMapper modelMapper = new ModelMapper();
        Movie movie =
                modelMapper.map(movieClient.findById(booking.getMovies()).getData(),
                        Movie.class);
        ShowTimes showTimes =
                modelMapper.map(showTimesClient.findById(booking.getShowTimeId()).getData(),
                ShowTimes.class);
        User user =
                modelMapper.map(userClient.findById(booking.getUserId()).getData(),
                        User.class);
        booking.setMovie(movie);
        booking.setShowTimes(showTimes);
        booking.setUser(user);
        return bookingRepository.findByUserId(userId);
    }
}
