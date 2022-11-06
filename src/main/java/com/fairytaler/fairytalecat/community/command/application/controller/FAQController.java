package com.fairytaler.fairytalecat.community.command.application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FAQController {

    @GetMapping("/test")
    public String test(){
        return "성공!";
    }

}
