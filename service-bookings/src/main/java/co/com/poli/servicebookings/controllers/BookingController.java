package co.com.poli.servicebookings.controllers;

import co.com.poli.servicebookings.entities.Booking;
import co.com.poli.servicebookings.services.BookingService;
import co.com.poli.servicebookings.utils.ErrorMessage;
import co.com.poli.servicebookings.utils.Response;
import co.com.poli.servicebookings.utils.ResponseBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final ResponseBuilder responseBuilder;

    @PostMapping
    public Response save(@Valid @RequestBody Booking booking, BindingResult result){
        if(result.hasErrors()){
            return responseBuilder.failed(this.formatMessage(result));
        }
        bookingService.save(booking);
        return responseBuilder.success(booking);
    }

    @DeleteMapping("{id}")
    public Response delete(@PathVariable("id") Long id){
        Booking booking = bookingService.findById(id);
        if (booking==null){
            return responseBuilder.failed();
        }
        bookingService.delete(booking);
        return responseBuilder.success(booking);
    }

    @GetMapping
    public Response findAll(){
        List<Booking> bookings = bookingService.findAll();
        if (bookings.isEmpty()){
            return responseBuilder.failed();
        }
        return responseBuilder.success(bookings);
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable("id") Long id){
        Booking booking = bookingService.findById(id);
        if (booking==null){
            return responseBuilder.failed();
        }
        return responseBuilder.success(booking);
    }
    @GetMapping("/userId/{id}")
    public Response findByUserId(@PathVariable("id") Long userId){
        Booking booking = bookingService.findByUserId(userId);
        if (booking==null){
            return responseBuilder.failed();
        }
        return responseBuilder.success(booking);
    }

    private String formatMessage(BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String,String> error = new HashMap<>();
                    error.put(err.getField(),err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());

        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors)
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try{
            json = objectMapper.writeValueAsString(errorMessage);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return json;
    }
}
