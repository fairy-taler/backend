package com.fairytaler.fairytalecat.community.command.application.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@RestController
@RequestMapping("/test")
public class testController {

    private Connection con;


    public String test(){

        return "성공!";

    }
}
