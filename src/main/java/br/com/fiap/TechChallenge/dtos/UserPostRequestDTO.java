package br.com.fiap.TechChallenge.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserPostRequestDTO(

    @NotBlank(message = "O tipo de usuário não pode ser nulo")
    String userType,

    @NotBlank(message = "O nome não pode ser nulo")
    String name,

    @NotBlank(message = "O email não pode ser nulo")
    @Email(message = "Email no formato incorreto")
    String email,

    @NotBlank(message = "O login não pode ser nulo")
    String login,

    @NotBlank(message = "A senha não pode ser nula")
    String password,

    @Valid @JsonAlias("address")
    AddressPostRequestDTO address
) {}