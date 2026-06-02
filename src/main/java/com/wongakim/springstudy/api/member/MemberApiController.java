package com.wongakim.springstudy.api.member;

import com.wongakim.springstudy.api.member.dto.request.MemberRequest;
import com.wongakim.springstudy.api.member.dto.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberApiController {
    private final MemberService memberService;

    @GetMapping
    public List<MemberResponse> index() {
        return memberService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> show(@PathVariable Long id) {
        MemberResponse response = memberService.findById(id);
        return (response != null) ?
                ResponseEntity.ok(response) :
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<MemberResponse> join(@RequestBody MemberRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.join(request));
    }
}
