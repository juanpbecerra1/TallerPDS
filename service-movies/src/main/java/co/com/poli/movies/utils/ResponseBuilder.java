package co.com.poli.movies.utils;

import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@Component
public class ResponseBuilder {
    public Response success(){
        return Response.builder()
                .data(OK)
                .status(OK.value())
                .build();
    }

    public Response success(Object data){
        return Response.builder()
                .data(data)
                .status(OK.value())
                .build();
    }

    public Response failed(Object data){
        return Response.builder()
                .data(data)
                .status(INTERNAL_SERVER_ERROR.value())
                .build();
    }

    public Response failed(){
        return Response.builder()
                .data("INTERNAL_SERVER_ERROR")
                .status(INTERNAL_SERVER_ERROR.value())
                .build();
    }

}
