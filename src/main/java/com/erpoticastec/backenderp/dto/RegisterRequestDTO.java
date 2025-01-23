package com.erpoticastec.backenderp.dto;

public record RegisterRequestDTO (String nome, String sobrenome, String email, String username, String password, Long idfuncao) {
}