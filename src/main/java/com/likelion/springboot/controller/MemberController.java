package com.likelion.springboot.controller;

import com.likelion.springboot.domain.role.Lion;
import com.likelion.springboot.domain.role.Role;
import com.likelion.springboot.domain.role.Staff;
import com.likelion.springboot.dto.*;
import com.likelion.springboot.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/lions")
    public ResponseEntity<LionResponse> createLion(@RequestBody LionCreateRequest lionCreateRequest) {
        Role lion = memberService.createLion(lionCreateRequest);

        if (lion == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        LionResponse lionResponse = LionResponse.from((Lion) lion);
        return ResponseEntity.status(HttpStatus.CREATED).body(lionResponse);
    }

    @PostMapping("/staffs")
    public ResponseEntity<StaffResponse> createStaff(@RequestBody StaffCreateRequest staffCreateRequest) {
        Role staff = memberService.createStaff(staffCreateRequest);

        if (staff == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        StaffResponse staffResponse = StaffResponse.from((Staff) staff);
        return ResponseEntity.status(HttpStatus.CREATED).body(staffResponse);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getMember(@PathVariable("name") String name) {
        Role member = memberService.searchByName(name);

        if (member instanceof Lion lion) {
            return ResponseEntity.status(HttpStatus.OK).body(LionResponse.from((Lion) lion));
        }

        if (member instanceof Staff staff) {
            return ResponseEntity.status(HttpStatus.OK).body(StaffResponse.from((Staff) staff));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/lions/{name}")
    public ResponseEntity<LionResponse> updateLion(
            @PathVariable("name") String name,
            @RequestBody LionUpdateRequest lionUpdateRequest
    ) {
        Role lion = memberService.updateLion(name, lionUpdateRequest);

        if (lion == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(LionResponse.from((Lion) lion));
    }

    @PutMapping("/staffs/{name}")
    public ResponseEntity<StaffResponse> updateStaff(
            @PathVariable("name") String name,
            @RequestBody StaffUpdateRequest staffUpdateRequest
    ) {
        Role staff = memberService.updateStaff(name, staffUpdateRequest);

        if (staff == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(StaffResponse.from((Staff) staff));
    }

}
