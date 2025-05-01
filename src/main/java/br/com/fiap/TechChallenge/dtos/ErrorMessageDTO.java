package br.com.fiap.TechChallenge.dtos;

public record ErrorMessageDTO(
        String message,
        int status
) {}