package br.com.fiap.TechChallenge.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AddressPostRequestDTO(

        @NotBlank(message = "A rua não pode ser nula")
        String street,

        @Positive(message = "O número só pode ser acima de 0")
        Integer num,

        @NotNull(message = "O complemento não pode ser nulo")
        String complement,

        @NotBlank(message = "O país não pode ser nulo")
        String country,

        @NotBlank(message = "A cidade não pode ser nula")
        String city,

        @NotBlank(message = "O estado não pode ser nulo")
        String state,

        @NotBlank(message = "O CEP não pode ser nulo")
        String zipcode
) {}