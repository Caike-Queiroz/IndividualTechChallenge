package br.com.fiap.TechChallenge.services;

import br.com.fiap.TechChallenge.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticate(String login, String password) {

        return this.userRepository.findByLogin(login)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }
}