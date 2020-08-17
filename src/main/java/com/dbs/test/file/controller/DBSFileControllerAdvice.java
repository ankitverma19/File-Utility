package com.dbs.test.file.controller;
import com.dbs.test.file.exception.FileDirectoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DBSFileControllerAdvice {

    /**
     * Rest exception handler for {@link FileDirectoryNotFoundException}
     * @param exception the {@link FileDirectoryNotFoundException} thrown by service.
     * @return the error {@link ResponseEntity}
     */
    @ExceptionHandler(value = FileDirectoryNotFoundException.class)
    public ResponseEntity<Object> exception(final FileDirectoryNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
