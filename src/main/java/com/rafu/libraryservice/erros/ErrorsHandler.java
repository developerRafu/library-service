package com.rafu.libraryservice.erros;

import com.rafu.libraryservice.erros.helpers.InternalErrorsEnum;
import com.rafu.libraryservice.erros.helpers.MessagesEnum;
import com.rafu.libraryservice.erros.models.DefaultError;
import com.rafu.libraryservice.vo.InvalidHeaderException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Slf4j
@ControllerAdvice
@RestController
public class ErrorsHandler {

    @ExceptionHandler(BookAlreadyCreate.class)
    public ResponseEntity<DefaultError> handleBookAlreadyCreate(
            final BookAlreadyCreate ex,
            final WebRequest request
    ) {
        log.error(InternalErrorsEnum.INTERNAL_ERROR.getFormattedMessage(ex.getClass().getName()), ex.getCause());
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
        log.error(InternalErrorsEnum.INTERNAL_ERROR.getFormattedMessage(ex.getClass().getName()), ex.getCause());
        final var error = DefaultError
                .builder()
                .message(MessagesEnum.ENTITY_NOT_FOUND.getFormattedMessage(ex.getEntity()))
                .code(HttpStatus.NOT_FOUND.value())
                .build();
        return ResponseEntity.status(error.getCode()).body(error);
    }

    @ExceptionHandler(EmailInUseException.class)
    public ResponseEntity<DefaultError> handleEmailInUseException(
            final EmailInUseException ex,
            final WebRequest request
    ) {
        log.error(InternalErrorsEnum.INTERNAL_ERROR.getFormattedMessage(ex.getClass().getName()), ex.getCause());
        final var error = DefaultError
                .builder()
                .message(MessagesEnum.EMAIL_ALREADY_SAVED.getFormattedMessage(ex.getEmail()))
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
        return ResponseEntity.status(error.getCode()).body(error);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<DefaultError> handleUnauthorizedException(
            final UnauthorizedException ex,
            final WebRequest request
    ) {
        log.error(InternalErrorsEnum.INTERNAL_ERROR.getFormattedMessage(ex.getClass().getName()), ex.getCause());
        final var error = DefaultError
                .builder()
                .message(MessagesEnum.NOT_AUTHORIZED.getFormattedMessage())
                .code(HttpStatus.UNAUTHORIZED.value())
                .build();
        return ResponseEntity.status(error.getCode()).body(error);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<DefaultError> handleWrongPasswordException(
            final WrongPasswordException ex,
            final WebRequest request
    ) {
        log.error(InternalErrorsEnum.INTERNAL_ERROR.getFormattedMessage(ex.getClass().getName()), ex.getCause());
        final var error = DefaultError
                .builder()
                .message(MessagesEnum.WRONG_PASSWORD.getFormattedMessage())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
        return ResponseEntity.status(error.getCode()).body(error);
    }

    @ExceptionHandler(InvalidHeaderException.class)
    public ResponseEntity<DefaultError> handleInvalidHeaderException(
            final InvalidHeaderException ex,
            final WebRequest request
    ) {
        log.error(InternalErrorsEnum.INTERNAL_ERROR.getFormattedMessage(ex.getClass().getName()), ex.getCause());
        final var error = DefaultError
                .builder()
                .message(MessagesEnum.INVALID_HEADER.getFormattedMessage())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
        return ResponseEntity.status(error.getCode()).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DefaultError> handleException(
            final Exception ex,
            final WebRequest request
    ) {
        log.error(InternalErrorsEnum.INTERNAL_ERROR.getFormattedMessage(ex.getClass().getName()), ex.getCause());
        final var error = DefaultError
                .builder()
                .message(MessagesEnum.INTERNAL_SERVER_ERROR.getFormattedMessage())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .details(List.of(ex.getMessage()))
                .build();
        return ResponseEntity.status(error.getCode()).body(error);
    }
}
