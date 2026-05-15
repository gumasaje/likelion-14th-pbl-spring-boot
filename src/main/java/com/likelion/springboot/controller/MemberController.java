package com.likelion.springboot.controller;

import com.likelion.springboot.domain.role.Lion;
import com.likelion.springboot.domain.role.Role;
import com.likelion.springboot.domain.role.Staff;
import com.likelion.springboot.dto.*;
import com.likelion.springboot.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Member Management", description = "멤버 관리 API")
@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @Operation(summary = "Lion 등록", description = "새로운 Lion 멤버를 등록한다.")
    @PostMapping("/lions")
    public ResponseEntity<LionResponse> createLion(@RequestBody LionCreateRequest lionCreateRequest) {
        Role lion = memberService.createLion(lionCreateRequest);

        if (lion == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        LionResponse lionResponse = LionResponse.from((Lion) lion);
        return ResponseEntity.status(HttpStatus.CREATED).body(lionResponse);
    }

    @Operation(summary = "Staff 등록", description = "새로운 Staff 멤버를 등록한다.")
    @PostMapping("/staffs")
    public ResponseEntity<StaffResponse> createStaff(@RequestBody StaffCreateRequest staffCreateRequest) {
        Role staff = memberService.createStaff(staffCreateRequest);

        if (staff == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        StaffResponse staffResponse = StaffResponse.from((Staff) staff);
        return ResponseEntity.status(HttpStatus.CREATED).body(staffResponse);
    }

    @Operation(summary = "단일 멤버 조회", description = "이름을 통해 특정 멤버의 정보를 조회한다.")
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

    @Operation(summary = "Lion 수정", description = "기존 Lion 멤버의 정보를 수정한다.")
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

    @Operation(summary = "Staff 수정", description = "기존 Staff 멤버의 정보를 수정한다.")
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

    @Operation(summary = "멤버 삭제", description = "이름을 통해 멤버를 삭제한다.")
    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteMember(@PathVariable("name") String name) {

        if (!memberService.deleteMember(name)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    // Bonus 1
    @Operation(summary = "전체 멤버 조회", description = "등록된 모든 멤버 목록을 조회한다.")
    @GetMapping
    public ResponseEntity<List<?>> getMembers() {
        return ResponseEntity.ok(
                memberService.getAllMembers().stream()
                        .map(member -> {
                            if (member instanceof Lion lion) return LionResponse.from((Lion) lion);
                            if (member instanceof Staff staff) return StaffResponse.from((Staff) staff);
                            return null;
                        })
                        .toList()
        );
    }

    // Bonus 2
    @Operation(summary = "멤버 검색", description = "이름 쿼리 파라미터를 통해 멤버를 검색한다.")
    @GetMapping("/search")
    public ResponseEntity<?> searchByName(@RequestParam String name) {
        Role member = memberService.searchByName(name);

        if (member instanceof Lion lion) return ResponseEntity.ok(LionResponse.from((Lion) lion));
        if (member instanceof Staff staff) return ResponseEntity.ok(StaffResponse.from((Staff) staff));

        return ResponseEntity.notFound().build();
    }

}
