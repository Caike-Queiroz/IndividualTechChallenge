package br.com.fiap.TechChallenge.dtos;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordRequestDTO(

        @NotBlank(message = "Senha atual é obrigatória")
        String currentPassword,

        @NotBlank(message = "Nova senha é obrigatória")
        String newPassword
) {}