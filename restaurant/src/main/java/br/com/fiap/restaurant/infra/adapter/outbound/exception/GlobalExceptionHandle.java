package br.com.fiap.restaurant.infra.adapter.outbound.exception;

import org.hibernate.sql.exec.ExecutionException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityReturnValueHandler;

import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandle extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {ChangeSetPersister.NotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(RuntimeException e, WebRequest request) {
        var response = new ResponseException(
                ChangeSetPersister.NotFoundException.class.getTypeName(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                HttpHeaders.SERVER
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {NullPointerException.class})
    protected ResponseEntity<Object> handleNullPoint(RuntimeException e, WebRequest request) {
        var response = new ResponseException(
                NullPointerException.class.getTypeName(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                e.getMessage().replaceAll("\\\".*?\\\"", ""),
                request.getDescription(true).split(";")[0].replace("uri=", "")
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {SQLException.class})
    protected ResponseEntity<Object> handleSQLException(RuntimeException e, WebRequest request) {
        var response = new ResponseException(
                ExecutionException.class.getTypeName(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                request.getDescription(true).split(";")[0].replace("uri=", "")
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
