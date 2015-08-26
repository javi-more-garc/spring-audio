/**
 * 
 */
package com.jmg.sa.rest;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jmg.sa.exception.OperationNotAuthorizedException;

/**
 * @author Javier Moreno Garcia
 *
 */
@ControllerAdvice(basePackages = "com.jmg.sa.rest")
public class CustomRestControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(CustomRestControllerAdvice.class);

    @ExceptionHandler({ EntityNotFoundException.class })
    public ResponseEntity<StatusResponse> notFoundError(Exception exception) {
        
        return response(NOT_FOUND, exception);
    }
    
    @ExceptionHandler({ OperationNotAuthorizedException.class })
    public ResponseEntity<StatusResponse> operationNotAuthorizedError(Exception exception) {
        
        return response(FORBIDDEN, exception);
    }   
    
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<StatusResponse> generalError(Exception exception) {

        return response(INTERNAL_SERVER_ERROR, exception);
    }

    //
    //

    public static class StatusResponse {

        private String status;
        private String mesage;

        public StatusResponse() {

        }

        public StatusResponse(String status) {
            this(status, null);
        }

        public StatusResponse(String status, String mesage) {
            this.status = status;
            this.mesage = mesage;
        }

        public String getStatus() {
            return this.status;
        }

        public String getMessage() {
            return this.mesage;
        }

        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
        }
    }   
    
    //
    // private methods
    
    
   
    public ResponseEntity<StatusResponse> response(HttpStatus status, Exception exception) {

        logger.debug("Handling exception [{}]", exception.getMessage());
        
        return ResponseEntity.status(status).body(new StatusResponse("ko", exception.getMessage()));

    }
}
