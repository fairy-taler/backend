package com.fairytaler.fairytalecat.community.command.application.controller;

import com.fairytaler.fairytalecat.community.command.application.dto.NoticeRequestDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@RestController
        @RequestMapping("/test")
        public class testController {

        @PostMapping("/notices")
        public String test(@ModelAttribute("orderReq") NoticeRequestDTO noticeRequest){

            /* 입력한 공지사항 정보 출력 */
            System.out.println("파라미터 : " + noticeRequest);
            /* 내용 입력 */


        return "성공!";
    }
}
