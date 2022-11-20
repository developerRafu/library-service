package com.rafu.libraryservice.controllers.interceptors;

import com.rafu.libraryservice.erros.UnauthorizedException;
import com.rafu.libraryservice.services.ILoginService;
import com.rafu.libraryservice.vo.InvalidHeaderException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

import static com.rafu.libraryservice.helpers.ConstantsEnum.*;
import static org.springframework.util.ObjectUtils.isEmpty;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RestInterceptor implements AsyncHandlerInterceptor {
    private final ILoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (isNotRequiredAuthRequest(request)) {
            return true;
        } else {
            validateAuthorization(request);
        }
        return true;
    }

    private boolean isNotRequiredAuthRequest(final HttpServletRequest request) {
        return (isLoginRequest(request) || isCreateUserRequest(request) || isSwaggerRequest(request));
    }

    private boolean isCreateUserRequest(HttpServletRequest request) {
        return request.getRequestURI().contains(USERS.getValue()) && Objects.equals(request.getMethod(), POST_METHOD.getValue());
    }

    private boolean isLoginRequest(HttpServletRequest request) {
        return request.getRequestURI().contains(LOGIN.getValue()) && Objects.equals(request.getMethod(), POST_METHOD.getValue());
    }

    private boolean isSwaggerRequest(HttpServletRequest request) {
        return request.getRequestURI().contains(SWAGGER_UI.getValue()) && Objects.equals(request.getMethod(), GET_METHOD.getValue());
    }

    private void validateAuthorization(HttpServletRequest request) {
        final var token = request.getHeader(AUTHORIZATION_HEADER.getValue());
        if (isEmpty(token) || !token.contains(BEARER.getValue())) {
            throw new InvalidHeaderException(AUTHORIZATION_HEADER.getValue());
        }
        if (!loginService.isAuthorized(token)) {
            throw new UnauthorizedException();
        }
    }
}
