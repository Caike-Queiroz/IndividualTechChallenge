package br.com.fiap.TechChallenge.entities;

import br.com.fiap.TechChallenge.dtos.UserPostRequestDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class User {

    private Long id;
    private UserType userType;
    private String name;
    private String email;
    private String login;
    private String password;
    private LocalDateTime lastModifiedDate;
    private Long addressId;

    public User(UserPostRequestDTO user, Address address) {

        this.userType = UserType.fromString(user.userType().trim().toUpperCase());
        this.name = user.name();
        this.email = user.email();
        this.login = user.login();
        this.password = user.password();
        this.lastModifiedDate = LocalDateTime.now();
        this.addressId = address.getId();
    }

    public User(String name, String email, String login) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.lastModifiedDate = LocalDateTime.now();
    }
}