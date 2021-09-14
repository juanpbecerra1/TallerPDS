package co.com.poli.servicebookings.services;

import co.com.poli.servicebookings.entities.Booking;

import java.util.List;

public interface BookingService{
    List<Booking> findAll();
    void save(Booking booking);
    Booking findById(Long id);
    void delete(Booking booking);
    Booking findByUserId(Long userId);
}
