package co.com.poli.servicebookings.client;

import co.com.poli.servicebookings.utils.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Component
@FeignClient(name = "service-showtimes")
public interface ShowTimesClient {
    @GetMapping("/showtimes/{id}")
    Response findById(@PathVariable("id") Long id);
}
