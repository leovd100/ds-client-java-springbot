package dsclient.dsclient.controllerException;

import dsclient.dsclient.serviceExceptions.ClientException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<?> entityNotFound(ClientException entity, HttpServletRequest httpservlet){
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError standardError = new StandardError(
                Instant.now(),
                status.value(),
                "Resource not found",
                entity.getMessage(),
                httpservlet.getRequestURI());
        return ResponseEntity.status(status).body(standardError);


    }




}
