package com.rafu.libraryservice.services;

import com.rafu.libraryservice.domain.User;
import com.rafu.libraryservice.erros.EmailInUseException;
import com.rafu.libraryservice.repositories.UserRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements IUserService {
    private final UserRespository respository;
    @Override
    public Optional<User> findByEmail(final String email) {
        return respository.findByEmail(email);
    }

    @Override
    public User create(final User user) {
        if (this.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailInUseException(user.getEmail());
        }
        return respository.save(user);
    }
}