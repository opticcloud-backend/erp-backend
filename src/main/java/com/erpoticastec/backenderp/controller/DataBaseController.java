package com.erpoticastec.backenderp.controller;

import com.erpoticastec.backenderp.service.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DataBaseController {

    @Autowired
    private DataBaseService databaseService;

    @GetMapping("/testdb")
    public String testDatabase() {
        return databaseService.testDatabase();
    }
}
