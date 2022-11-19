package com.rafu.libraryservice.erros;

import com.rafu.libraryservice.erros.helpers.MessagesEnum;
import com.rafu.libraryservice.erros.models.DefaultError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class ErrorsHandler {

    @ExceptionHandler(BookAlreadyCreate.class)
    public ResponseEntity<DefaultError> handleBookAlreadyCreate(
            final BookAlreadyCreate ex,
            final WebRequest request
    ) {
        final var error = DefaultError
                .builder()
                .message(MessagesEnum.BOOK_ALREADY_CREATED.getFormattedMessage(ex.getName()))
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
        return ResponseEntity.status(error.getCode()).body(error);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<DefaultError> handleNotFoundException(
            final NotFoundException ex,
            final WebRequest request
    ) {
        final var error = DefaultError
                .builder()
                .message(MessagesEnum.ENTITY_NOT_FOUND.getFormattedMessage(ex.getEntity()))
                .code(HttpStatus.NOT_FOUND.value())
                .build();
        return ResponseEntity.status(error.getCode()).body(error);
    }
}
