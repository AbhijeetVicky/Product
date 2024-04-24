package dev.abhijeet.productService.advice;

import dev.abhijeet.productService.dtos.ErrorDto;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDto> handleNullPointerException(){
        ErrorDto errorDto = new  ErrorDto();
        errorDto.setMessage("Something went wrong. Please try again");

        return  new  ResponseEntity<>(errorDto,
                HttpStatusCode.valueOf(404));
    }

}