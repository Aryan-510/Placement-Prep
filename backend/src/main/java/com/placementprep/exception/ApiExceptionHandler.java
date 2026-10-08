package com.placementprep.exception;

import org.springframework.http.*; import org.springframework.web.bind.MethodArgumentNotValidException; import org.springframework.web.bind.annotation.*; import org.springframework.http.converter.HttpMessageNotReadableException;
import java.time.Instant; import java.util.*;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class) ResponseEntity<Map<String,Object>> notFound(NoSuchElementException ex){return response(HttpStatus.NOT_FOUND,ex.getMessage());}
    @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<Map<String,Object>> invalid(MethodArgumentNotValidException ex){StringJoiner messages=new StringJoiner("; ");ex.getBindingResult().getFieldErrors().forEach(e->messages.add(e.getField()+": "+e.getDefaultMessage()));ex.getBindingResult().getGlobalErrors().forEach(e->messages.add(e.getDefaultMessage()));return response(HttpStatus.BAD_REQUEST,messages.toString());}
    @ExceptionHandler({HttpMessageNotReadableException.class,IllegalArgumentException.class}) ResponseEntity<Map<String,Object>> badRequest(Exception ex){return response(HttpStatus.BAD_REQUEST,"Invalid request. Check field values and enum names.");}
    @ExceptionHandler(Exception.class) ResponseEntity<Map<String,Object>> serverError(Exception ex){return response(HttpStatus.INTERNAL_SERVER_ERROR,"An unexpected server error occurred.");}
    private ResponseEntity<Map<String,Object>> response(HttpStatus status,String message){Map<String,Object> body=new LinkedHashMap<>();body.put("timestamp",Instant.now());body.put("status",status.value());body.put("error",status.getReasonPhrase());body.put("message",message);return ResponseEntity.status(status).body(body);}
}

