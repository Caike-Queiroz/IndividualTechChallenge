package br.com.fiap.TechChallenge.repositories;

import br.com.fiap.TechChallenge.entities.User;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImp implements UserRepository {

    private final JdbcClient jdbcClient;

    public UserRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<User> findById(Long id) {

        return this.jdbcClient
                .sql("SELECT * FROM users WHERE id = :id")
                .param("id", id)
                .query(User.class)
                .optional();
    }

    @Override
    public Optional<User> findByEmail(String email) {

        return this.jdbcClient
                .sql("SELECT * FROM users WHERE email = :email")
                .param("email", email)
                .query(User.class)
                .optional();
    }

    @Override
    public Optional<User> findByLogin(String login) {

        return this.jdbcClient
                .sql("SELECT * FROM users WHERE login = :login")
                .param("login", login)
                .query(User.class)
                .optional();
    }

    @Override
    public List<User> findAll(int size, int offset) {

        return this.jdbcClient
                .sql("SELECT * FROM users LIMIT :size OFFSET :offset")
                .param("size", size)
                .param("offset", offset)
                .query(User.class)
                .list();
    }

    @Override
    public Integer save(User user) {

        return this.jdbcClient
                .sql("INSERT INTO users (user_type, name, email, login, password, last_modified_date, address_id)" +
                        " VALUES (:userType ,:name, :email, :login, :password, :lastModifiedDate, :addressId)")
                .param("userType", user.getUserType().toString())
                .param("name", user.getName())
                .param("email", user.getEmail())
                .param("login", user.getLogin())
                .param("password", user.getPassword())
                .param("lastModifiedDate", LocalDateTime.now())
                .param("addressId", user.getAddressId())
                .update();
    }

    @Override
    public Integer update(User user, Long id) {

        return this.jdbcClient
                .sql("UPDATE users SET name = :name, email = :email, login = :login, last_modified_date = :lastModifiedDate WHERE id = :id")
                .param("id", id)
                .param("name", user.getName())
                .param("email", user.getEmail())
                .param("login", user.getLogin())
                .param("lastModifiedDate", LocalDateTime.now())
                .update();
    }

    @Override
    public Integer changePassword(String newPassword, Long id) {

        return this.jdbcClient
                .sql("UPDATE users SET password = :newPassword, last_modified_date = :lastModifiedDate WHERE id = :id")
                .param("id", id)
                .param("newPassword", newPassword)
                .param("lastModifiedDate", LocalDateTime.now())
                .update();
    }

    @Override
    public Integer delete(Long id) {

        return this.jdbcClient
                .sql("DELETE FROM users WHERE id = :id")
                .param("id", id)
                .update();
    }
}