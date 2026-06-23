package com.roadready.config;
import com.roadready.exceptions.*;
import com.roadready.util.ResponseUtility;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    private ResponseUtility responseUtility;
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseUtility> handleResourceNotFoundException(
            ResourceNotFoundException e
    ){
        logger.warn("Resource look up failed: {}", e.getMessage());
        responseUtility.setMessage(e.getMessage());
         return ResponseEntity.badRequest().body(responseUtility);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ){
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> errors=  bindingResult.getFieldErrors();
        Map<String, String> map = new HashMap<>();
        for(FieldError error : errors){
            map.put(error.getField(), error.getDefaultMessage());
            logger.error("Field {} - message: {}", error.getField(), error.getDefaultMessage());


        }
        return ResponseEntity
                .badRequest()
                .body(map);


    }

    @ExceptionHandler(CarNotAvailableException.class)
    public ResponseEntity<ResponseUtility> handleCarNotAvailableException(CarNotAvailableException e){
        logger.warn("Booking restriction triggered: {}", e.getMessage());
        responseUtility.setMessage(e.getMessage());

        return ResponseEntity
                .badRequest()
                .body(responseUtility);

    }

    @ExceptionHandler(InvalidBookingDateException.class)
    public ResponseEntity<Map<String, String>> handleInvalidBookingDateException(
            InvalidBookingDateException e
    ){
        Map<String, String> map = new HashMap<>();

        map.put(e.getField(),e.getMessage());
        return ResponseEntity.badRequest()
                .body(map);


    }


    @ExceptionHandler(IllegalBookingStatusException.class)
    public ResponseEntity<ResponseUtility> handleIllegalBookingStatusException(
            IllegalBookingStatusException e
    ){
        responseUtility.setMessage(e.getMessage());
        return ResponseEntity.badRequest()
                .body(responseUtility);
    }


    @ExceptionHandler(CouponException.class)
    public ResponseEntity<ResponseUtility> handleCouponException(
            CouponException e
    ){
        logger.warn("Coupon validation failed: {}", e.getMessage());
        responseUtility.setMessage(e.getMessage());
        return ResponseEntity.badRequest()
                .body(responseUtility);
    }


    @ExceptionHandler(InvalidOwnerShipException.class)
    public ResponseEntity<ResponseUtility> handleUnauthorizedAccessException(
            InvalidOwnerShipException e
    ){
        responseUtility.setMessage(e.getMessage());
        return ResponseEntity.badRequest()
                .body(responseUtility);
    }

    @ExceptionHandler(UserAlreadyPresentException.class)
    public ResponseEntity<ResponseUtility> handleUserAlreadyPresentException(
            UserAlreadyPresentException e
    ){
        responseUtility.setMessage(e.getMessage());
        return ResponseEntity.badRequest()
                .body(responseUtility);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ResponseUtility> handleFileNotFoundException(
            FileNotFoundException e
    ){
        responseUtility.setMessage(e.getMessage());
        return ResponseEntity
                .badRequest()
                .body(responseUtility);
    }



    @ExceptionHandler(IOException.class)
    public ResponseEntity<ResponseUtility> handleIOException(
            IOException e
    ){
        responseUtility.setMessage(e.getMessage());
        return ResponseEntity
                .badRequest()
                .body(responseUtility);
    }


    @ExceptionHandler(FileInvalidExtensionException.class)
    public ResponseEntity<ResponseUtility> handleFileInvalidExtensionException(
            FileInvalidExtensionException e
    ){
        responseUtility.setMessage(e.getMessage());
        return ResponseEntity
                .badRequest()
                .body(responseUtility);
    }


}
