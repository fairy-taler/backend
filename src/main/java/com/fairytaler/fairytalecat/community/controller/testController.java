package com.fairytaler.fairytalecat.community.controller;

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

    @BeforeEach
    public void setConnection() throws ClassNotFoundException, SQLException {

        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "C##SHOP";
        String password = "SHOP";

        Class.forName(driver);

        con = DriverManager.getConnection(url, user, password);
    }

    @AfterEach
    public void closeConnection() throws SQLException {
        con.close();
    }

    @GetMapping
    public String test(){

        return "성공!";

    }
}
