package br.com.fiap.TechChallenge.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserPatchRequestDTO(

    @NotBlank(message = "O nome não pode ser nulo")
    String name,

    @NotBlank(message = "O email não pode ser nulo")
    @Email(message = "Email no formato incorreto")
    String email,

    @NotBlank(message = "O login não pode ser nulo")
    String login

    //private Address address;
) {}