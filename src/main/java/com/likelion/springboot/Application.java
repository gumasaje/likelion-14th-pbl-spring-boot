package com.likelion.springboot;

import com.likelion.springboot.service.MemberService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);

        MemberService memberService = context.getBean(MemberService.class);
        System.out.println("서비스 가져오기 성공: " + memberService);

    }
}
