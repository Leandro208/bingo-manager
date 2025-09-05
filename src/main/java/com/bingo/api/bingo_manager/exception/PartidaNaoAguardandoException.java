package com.bingo.api.bingo_manager.exception;

public class PartidaNaoAguardandoException extends RuntimeException{

    public PartidaNaoAguardandoException() {
        super("A partida não está com o status 'AGUARDANDO'. Não é possível iniciar.");
    }

    public PartidaNaoAguardandoException(String mensagem) {
        super(mensagem);
    }
}
