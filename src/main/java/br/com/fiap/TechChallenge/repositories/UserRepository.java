package br.com.fiap.TechChallenge.repositories;

import br.com.fiap.TechChallenge.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    Optional<User> findByLogin(String login);

    List<User> findAll(int size, int offset);

    Integer save(User user);

    Integer update(User user, Long id);

    Integer changePassword(String newPassword, Long id);

    Integer delete(Long id);
}