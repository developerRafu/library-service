package com.rafu.libraryservice.services;

import com.rafu.libraryservice.components.JWTUtil;
import com.rafu.libraryservice.components.SecretProperties;
import com.rafu.libraryservice.erros.WrongPasswordException;
import com.rafu.libraryservice.helpers.ConstantsEnum;
import com.rafu.libraryservice.helpers.UserHelper;
import com.rafu.libraryservice.vo.requests.LoginRequest;
import com.rafu.libraryservice.vo.responses.TokenResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LoginServiceImplTest {
    IUserService userService;
    JWTUtil jwt;
    ILoginService service;
    SecretProperties secretProperties;

    @BeforeEach
    void setUp() {
        userService = mock(UserServiceImpl.class);
        secretProperties = mock(SecretProperties.class);
        jwt = spy(new JWTUtil(secretProperties));
        service = new LoginServiceImpl(userService, jwt);
        when(secretProperties.getSecret()).thenReturn(ConstantsEnum.MOCKED_SECRET.getValue());
        when(secretProperties.getExpiration()).thenReturn(Long.valueOf(ConstantsEnum.MOCKED_EXPIRATION.getValue()));
    }

    @Nested
    class LoginTests {
        @Test
        void shouldReturnAValidLogin() {
            final var token = getToken();
            final var request = getRequest();
            final var user = UserHelper.getUser();
            when(userService.findByEmail(any())).thenReturn(Optional.of(user));
            when(jwt.getToken(any())).thenReturn(token.getToken());
            final var result = service.login(request);
            assertEquals(token.getToken(), result.getToken());
            assertEquals(token.getType(), result.getType());
        }

        @Test
        void shouldThrowsExceptionWhenPasswordIsWrong() {
            final var request = getRequest();
            request.setPassword("");
            final var user = UserHelper.getUser();
            when(userService.findByEmail(any())).thenReturn(Optional.of(user));
            assertThrows(WrongPasswordException.class, () -> service.login(request));
        }
    }

    @Nested
    class isAuthorizedTests {
        @Test
        void shouldReturnFalseWhenTokenIsInValid() {
            when(jwt.isValidToken(getToken().getToken())).thenReturn(Boolean.TRUE);
            when(userService.findByEmail(any())).thenReturn(Optional.of(UserHelper.getUser()));
            final var result = service.isAuthorized(getToken().getToken());
            assertFalse(result);
        }

        @Test
        void shouldReturnTrueWhenTokenIsValid() {
            final var token = jwt.getToken(ConstantsEnum.MOCKED_EMAIL.getValue());
            when(userService.findByEmail(any())).thenReturn(Optional.of(UserHelper.getUser()));
            when(jwt.isValidToken(any())).thenCallRealMethod();
            final var result = service.isAuthorized("Bearer " + token);
            assertTrue(result);
        }

    }

    private TokenResponse getToken() {
        return TokenResponse.builder()
                .token(ConstantsEnum.TOKEN.getValue())
                .type(ConstantsEnum.BEARER.getValue())
                .build();
    }

    private LoginRequest getRequest() {
        return LoginRequest
                .builder()
                .email(ConstantsEnum.MOCKED_EMAIL.getValue())
                .password(ConstantsEnum.MOCKED_PASSWORD.getValue())
                .build();
    }
}
