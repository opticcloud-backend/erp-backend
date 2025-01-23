package com.erpoticastec.backenderp.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DataBaseService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String testDatabase() {
        Integer result = jdbcTemplate.queryForObject("SELECT 1 + 1", Integer.class);
        return "Resultado da consulta: " + result;
    }
}

