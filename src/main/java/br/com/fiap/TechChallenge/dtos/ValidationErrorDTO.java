package br.com.fiap.TechChallenge.dtos;

import java.util.List;

public record ValidationErrorDTO(List<String> errors, int status) {}