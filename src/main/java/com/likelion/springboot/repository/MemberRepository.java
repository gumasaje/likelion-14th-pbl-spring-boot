package com.likelion.springboot.repository;

import com.likelion.springboot.role.Role;

import java.util.List;

public interface MemberRepository {
    void save(Role member);

    Role findByName(String name);

    List<Role> findAll();

    boolean existsByName(String name);
}