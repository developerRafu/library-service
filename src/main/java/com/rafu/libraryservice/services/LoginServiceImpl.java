package com.rafu.libraryservice.services;

import com.rafu.libraryservice.components.JWTUtil;
import com.rafu.libraryservice.erros.NotFoundException;
import com.rafu.libraryservice.erros.UnauthorizedException;
import com.rafu.libraryservice.erros.WrongPasswordException;
import com.rafu.libraryservice.vo.requests.LoginRequest;
import com.rafu.libraryservice.vo.responses.TokenResponse;
import com.rafu.libraryservice.vo.responses.enums.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginServiceImpl implements ILoginService {
    private final IUserService userService;
    private final JWTUtil jwt;

    @Override
    public TokenResponse login(LoginRequest request) {
        final var user = userService
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException("User"));

        if (!isOkPassword(request.getPassword(), user.getPassword())) {
            throw new WrongPasswordException();
        }

        return TokenResponse.builder()
                .token(getToken(user.getEmail()))
                .type(TokenType.BEARER.getDesc()).build();
    }

    private boolean isOkPassword(String passwordRequest, String passwordUser) {
        return Objects.equals(passwordRequest, passwordUser);
    }

    private String getToken(final String email) {
        return jwt.getToken(email);
    }

    @Override
    public boolean isAuthorized(final String token) {
        final var onlyToken = token.substring(7);
        final var isValid = jwt.isValidToken(onlyToken);
        return isValid && userService.findByEmail(jwt.getUsername(onlyToken)).isPresent();
    }
}
