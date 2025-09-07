package com.bingo.api.bingo_manager.exception;

public class PartidaEncerradaException extends RuntimeException {
    public PartidaEncerradaException(String message) {
        super(message);
    }

    public PartidaEncerradaException(){
        super("Essa partida já foi encerrada.");
    }
}
