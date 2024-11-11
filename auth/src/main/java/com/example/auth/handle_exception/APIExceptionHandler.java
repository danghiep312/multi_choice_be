package com.example.auth.handle_exception;

import com.example.auth.base.BaseResponse;
import com.example.auth.base.Status;
import com.example.auth.exeption.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class APIExceptionHandler {
    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public BaseResponse handleNotFoundException(NotFoundException e) {
        return BaseResponse.error(new Status("404", e.getMessage()));
    }

    @ExceptionHandler(value = IncorrectPasswordException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public BaseResponse handleIncorrectPasswordException(IncorrectPasswordException e) {
        return BaseResponse.error(new Status("400", e.getMessage()));
    }

    @ExceptionHandler(value = ForbiddenException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public BaseResponse handleForbiddenException(ForbiddenException e) {
        return BaseResponse.error(new Status("403", e.getMessage()));
    }

    @ExceptionHandler(value = ExistsException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public BaseResponse handleForbiddenException(ExistsException e) {
        return BaseResponse.error(new Status("409", e.getMessage()));
    }

    @ExceptionHandler(value = InvalidTokenException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public BaseResponse handleInvalidTokenException(InvalidTokenException e) {
        return BaseResponse.error(new Status("400", e.getMessage()));
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public BaseResponse handleUnauthorizedException(UnauthorizedException e) {
        return BaseResponse.error(new Status("401", e.getMessage()));
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse handleException(Exception e) {
        return BaseResponse.error(new Status("500", e.getMessage()));
    }
}
