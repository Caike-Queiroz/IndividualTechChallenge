package br.com.fiap.TechChallenge.controllers.handlers;

import br.com.fiap.TechChallenge.dtos.ErrorMessageDTO;
import br.com.fiap.TechChallenge.dtos.ValidationErrorDTO;
import br.com.fiap.TechChallenge.exceptions.BadRequestException;
import br.com.fiap.TechChallenge.exceptions.DuplicatedDataException;
import br.com.fiap.TechChallenge.exceptions.ResourceNotFoundException;
import br.com.fiap.TechChallenge.exceptions.WrongPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessageDTO> handleResourceNotFoundException(ResourceNotFoundException e) {

        var status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status.value()).body(new ErrorMessageDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(DuplicatedDataException.class)
    public ResponseEntity<ErrorMessageDTO> handleDuplicatedData(DuplicatedDataException e) {

        var status = HttpStatus.CONFLICT;
        return ResponseEntity.status(status.value()).body(new ErrorMessageDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessageDTO> handleIllegalArgumentException(IllegalArgumentException e) {

        var status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status.value()).body(new ErrorMessageDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorMessageDTO> handleBadRequestException(BadRequestException e) {

        var status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status.value()).body(new ErrorMessageDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<ErrorMessageDTO> handleWrongPasswordException(WrongPasswordException e) {

        var status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status.value()).body(new ErrorMessageDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        var status = HttpStatus.BAD_REQUEST;
        List<String> errors = new ArrayList<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errors.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        }
        return ResponseEntity.status(status.value()).body(new ValidationErrorDTO(errors, status.value()));
    }
}