package com.erpoticastec.backenderp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ClienteJaCadastradoException extends RuntimeException {
    public ClienteJaCadastradoException(String mensagem) {
        super(mensagem);
    }
}

