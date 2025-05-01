package br.com.fiap.TechChallenge.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(

        @NotBlank(message = "O login não pode estar nulo")
        String login,

        @NotBlank(message = "A senha não pode estar nula")
        String password
) {}