package co.com.poli.serviceusers.controller;

import co.com.poli.serviceusers.entities.User;
import co.com.poli.serviceusers.services.UserService;
import co.com.poli.serviceusers.utils.ErrorMessage;
import co.com.poli.serviceusers.utils.Response;
import co.com.poli.serviceusers.utils.ResponseBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ResponseBuilder builder;

    @GetMapping
    public Response findAll() {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            return builder.failed();
        }
        return builder.success(users);
    }

    @PostMapping
    public Response save(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return builder.failed(this.formatMessage(result));
        }
        userService.save(user);
        return builder.success(user);
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathParam("id") Long id){
        User user = userService.findById(id);
        if(user==null){
            return builder.failed();
        }
        userService.delete(user);
        return builder.success(user);
    }


    private String formatMessage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());

        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors)
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

}
