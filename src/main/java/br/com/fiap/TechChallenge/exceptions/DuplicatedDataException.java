package br.com.fiap.TechChallenge.exceptions;

public class DuplicatedDataException extends RuntimeException {

    public DuplicatedDataException(String message) {
        super(message);
    }
}
