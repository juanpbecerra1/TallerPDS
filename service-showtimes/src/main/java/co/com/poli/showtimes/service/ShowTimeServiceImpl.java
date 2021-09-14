package co.com.poli.showtimes.service;

import co.com.poli.showtimes.entities.ShowTime;
import co.com.poli.showtimes.repositories.ShowTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShowTimeServiceImpl implements ShowTimeService {

    private final ShowTimeRepository showTimeRepository;

    @Override
    public void save(ShowTime showTime) {
        showTimeRepository.save(showTime);
    }

    @Override
    public ShowTime findById(Long id) {
        return showTimeRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(ShowTime showTime) {
        showTimeRepository.delete(showTime);
    }

    @Override
    public void update(ShowTime showTime) {
        showTimeRepository.save(showTime);
    }
}
