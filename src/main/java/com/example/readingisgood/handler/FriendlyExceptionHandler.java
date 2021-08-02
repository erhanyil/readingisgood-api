package com.example.readingisgood.handler;


import com.example.readingisgood.constant.GenericError;
import com.example.readingisgood.dto.response.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FriendlyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({FriendlyException.class, RuntimeException.class, AccessDeniedException.class})
    public @ResponseBody
    ErrorResponseDto handleAllExceptions(Exception ex, WebRequest request) {
        ErrorResponseDto responseDto = new ErrorResponseDto(ex);
        if (ex instanceof AccessDeniedException) {
            responseDto.setCode(803);
        } else if (ex instanceof FriendlyException) {
            responseDto.setCode(((FriendlyException) ex).getCode());
            responseDto.setMsg(ex.getMessage());
        } else {
            responseDto.setMsg(GenericError.GENERIC_ERROR.name());
        }
        return responseDto;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(this.handleAllExceptions(ex, request), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(this.handleAllExceptions(ex, request), HttpStatus.NOT_FOUND);
    }
}