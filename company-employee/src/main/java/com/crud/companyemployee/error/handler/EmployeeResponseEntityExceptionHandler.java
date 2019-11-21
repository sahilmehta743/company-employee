package com.crud.companyemployee.error.handler;

import com.crud.companyemployee.error.dto.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class EmployeeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex,
                                                               HttpHeaders headers,
                                                               HttpStatus status,
                                                               WebRequest request) {
        String userMessage = ex.getVariableName() + " pathVariable is missing !!!";
        String developerMessage = ex.getMessage();
        ErrorResponse errorMessage = ErrorResponse.builder()
                .errorCode("EMP001")
                .developerMessage(developerMessage)
                .userMessage(userMessage).build();

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers,
                                                                         HttpStatus status,
                                                                         WebRequest request) {
        String userMessage = ex.getMethod() + " is not supported !!!";
        String developerMessage = ex.getMessage();
        ErrorResponse errorMessage = ErrorResponse.builder()
                .errorCode("EMP002")
                .developerMessage(developerMessage)
                .userMessage(userMessage).build();

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                     HttpHeaders headers,
                                                                     HttpStatus status,
                                                                     WebRequest request) {
        String userMessage = ex.getSupportedMediaTypes() + " are only supported.";
        String developerMessage = ex.getMessage();
        ErrorResponse errorMessage = ErrorResponse.builder()
                .errorCode("EMP003")
                .developerMessage(developerMessage)
                .userMessage(userMessage).build();

        return new ResponseEntity<>(errorMessage, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers,
                                                                   HttpStatus status,
                                                                   WebRequest request) {
        String userMessage = "Requested URL is not valid.";
        String developerMessage = ex.getMessage();
        ErrorResponse errorMessage = ErrorResponse.builder()
                .errorCode("EMP004")
                .developerMessage(developerMessage)
                .userMessage(userMessage).build();

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({Exception.class})
    public final ResponseEntity<Object> handleAllOtherException(Exception ex, WebRequest webRequest) {
        String error = ex.getLocalizedMessage();
        ErrorResponse errorMessage = ErrorResponse.builder()
                .errorCode("EMP005")
                .developerMessage(error)
                .userMessage(error).build();

        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
