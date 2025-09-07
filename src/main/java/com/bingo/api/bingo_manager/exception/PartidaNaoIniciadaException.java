package com.bingo.api.bingo_manager.exception;

public class PartidaNaoIniciadaException extends RuntimeException {
    public PartidaNaoIniciadaException(String message) {
        super(message);
    }
    public PartidaNaoIniciadaException() {
        super("A partida não foi iniciada");
    }
}
