package co.com.poli.showtimes.service;

import co.com.poli.showtimes.entities.ShowTime;

import java.util.List;

public interface ShowTimeService {
    void save(ShowTime showTime);
    ShowTime findById(Long id);
    void delete(ShowTime showTime);
    void update(ShowTime showTime);
}
