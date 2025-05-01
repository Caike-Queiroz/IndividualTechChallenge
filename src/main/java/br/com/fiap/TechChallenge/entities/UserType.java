package br.com.fiap.TechChallenge.entities;

public enum UserType {

    CLIENTE("CLIENTE"),
    DONO("DONO DE RESTAURANTE");

    private final String description;

    UserType(String description) {
        this.description = description;
    }

    public static UserType fromString(String text) {

        for (UserType tipoUsuario : UserType.values()) {
            if (tipoUsuario.description.equalsIgnoreCase(text)) {
                return tipoUsuario;
            }
        }

        throw new IllegalArgumentException("Nenhum tipo de usuário encontrado para o valor fornecido");
    }
}