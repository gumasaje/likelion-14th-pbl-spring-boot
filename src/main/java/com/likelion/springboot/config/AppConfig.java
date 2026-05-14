package com.likelion.springboot.config;

import com.likelion.springboot.repository.MemoryMemberRepository;
import com.likelion.springboot.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public MemoryMemberRepository memoryMemberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public MemberService memberService(MemoryMemberRepository memoryMemberRepository) {
        return new MemberService(memoryMemberRepository);
    }
}
