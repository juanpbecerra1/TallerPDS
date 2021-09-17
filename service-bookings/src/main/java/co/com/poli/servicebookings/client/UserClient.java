package co.com.poli.servicebookings.client;

import co.com.poli.servicebookings.utils.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import javax.websocket.server.PathParam;
@Component
@FeignClient(name = "service-users")
public interface UserClient {
    @GetMapping("{/users/{id}")
     Response findById(@PathParam("id") Long id);
}
