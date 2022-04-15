package pl.sda.CurrencyExchangeAPI.controller.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.sda.CurrencyExchangeAPI.service.ExchangeProcessingException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExchangeProcessingException.class)
    public ErrorResponse handleExchangeProcessingException(final ExchangeProcessingException exception) {
        log.debug("EXCEPTION!");
        return new ErrorResponse(exception.getMessage());
    }
}
