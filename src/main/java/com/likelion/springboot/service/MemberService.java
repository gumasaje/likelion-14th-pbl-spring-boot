package com.likelion.springboot.service;

import com.likelion.springboot.domain.role.Lion;
import com.likelion.springboot.domain.role.Staff;
import com.likelion.springboot.dto.LionCreateRequest;
import com.likelion.springboot.dto.LionUpdateRequest;
import com.likelion.springboot.dto.StaffCreateRequest;
import com.likelion.springboot.dto.StaffUpdateRequest;
import com.likelion.springboot.repository.MemberRepository;
import com.likelion.springboot.domain.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository repository;

    // @Autowired
    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Role createLion(LionCreateRequest lionCreateRequest) {
        Lion lion = new Lion(
                lionCreateRequest.getName(),
                lionCreateRequest.getMajor(),
                lionCreateRequest.getGeneration(),
                lionCreateRequest.getPart(),
                lionCreateRequest.getStudentId()
        );

        if (repository.existsByName(lion.getName())) {
            return null;
        }

        repository.save(lion);
        return lion;

    }

    public Role createStaff(StaffCreateRequest staffCreateRequest) {
        Staff staff = new Staff(
                staffCreateRequest.getName(),
                staffCreateRequest.getMajor(),
                staffCreateRequest.getGeneration(),
                staffCreateRequest.getPart(),
                staffCreateRequest.getPosition()
        );

        if (repository.existsByName(staff.getName())) {
            return null;
        }

        repository.save(staff);
        return staff;
    }

    public Role updateLion(String name, LionUpdateRequest lionUpdateRequest) {
        if (repository.findByName(name) == null) {
            return null;
        }

        Lion lion = new Lion(
                name,
                lionUpdateRequest.getMajor(),
                lionUpdateRequest.getGeneration(),
                lionUpdateRequest.getPart(),
                lionUpdateRequest.getStudentId()
        );

        repository.updateByName(name, lion);
        return lion;
    }

    public Role updateStaff(String name, StaffUpdateRequest staffUpdateRequest) {
        if (repository.findByName(name) == null) {
            return null;
        }

        Staff staff = new Staff(
                name,
                staffUpdateRequest.getMajor(),
                staffUpdateRequest.getGeneration(),
                staffUpdateRequest.getPart(),
                staffUpdateRequest.getPosition()
        );

        repository.updateByName(name, staff);
        return staff;
    }

    public boolean deleteMember(String name) {
        return repository.deleteByName(name);
    }

    public Role searchByName(String name) {
        return repository.findByName(name);
    }

    public List<Role> getAllMembers() {
        return repository.findAll();
    }

    public boolean isEmpty() {
        return repository.findAll().isEmpty();
    }
}
