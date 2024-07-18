package com.zoho.spring.errorhandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.zoho.util.ResponseCode;

@RestControllerAdvice
public class GlobalExceptionHandler{

    // public ResponseEntity<Map<String, String>>
    // handleValidationExceptions(MethodArgumentNotValidException ex) {
    // Map<String, String> errors = new HashMap<>();
    // ex.getBindingResult().getAllErrors().forEach((error) -> {
    // String fieldName = ((FieldError) error).getField();
    // String errorMessage = error.getDefaultMessage();
    // errors.put(fieldName, errorMessage);
    // });
    // return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    // }
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<CustomExceptionHandler> handleValidationExceptions(MethodArgumentNotValidException ex){
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach((error) -> {
//            String fieldName = error.getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler(
//                                        ResponseCode.INVALID_DATA,
//                                        errors,
//                                        "Invalid data. Please check details to resolve.",
//                                        "error");
//        return ResponseEntity.badRequest().body(customExceptionHandler);
//    }

//    @ExceptionHandler(CustomExceptionHandler.class)
//    public ResponseEntity<CustomExceptionHandler> validationExceptions(CustomExceptionHandler ex){
//
//        System.out.println("Enter inside CustomExceptionHandler :" + ex.getMessage());
//        Map<String, String> errors = new HashMap<>();
//        String fieldName = "details";
//        String errorMessage = null;
//        errors.put(fieldName, errorMessage);
//
//        CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler(
//                                        ResponseCode.INVALID_DATA,
//                                        errors,
//                                        "Invalid data. Please check details to resolve.",
//                                        "error");
//        return ResponseEntity.badRequest().body(customExceptionHandler);
//    }
    
  @ExceptionHandler(Exception.class)
  public ResponseEntity<  Map<String, String> > validationExceptions(Exception ex){

      System.out.println("Enter inside CustomExceptionHandler :" + ex.getMessage());
      Map<String, String> errors = new HashMap<>();
      String fieldName = "details";
      String errorMessage = null;
      errors.put(fieldName, errorMessage);

//      CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler(
//                                      ResponseCode.INVALID_DATA,
//                                      errors,
//                                      "Invalid data. Please check details to resolve.",
//                                      "error");
      return ResponseEntity.badRequest().body(errors);
  }
}
