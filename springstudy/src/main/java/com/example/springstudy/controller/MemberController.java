package com.example.springstudy.controller;

import com.example.springstudy.dto.MemberForm;
import com.example.springstudy.entity.Member;
import com.example.springstudy.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class MemberController {
    @Autowired  // 스프링 부트가 미리 생성해 놓은 리파지터리 객체 주입(DI)
    private MemberRepository memberRepository;  // 객체 선언

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/members/new")
    public String newMemberForm() {
        return "members/new";
    }

    @PostMapping("/join")
    public String joinMember(MemberForm form) {
        log.info(form.toString());  // DTO에 폼 데이터가 잘 담겼는지 확인

        // 1. DTO를 엔티티로 변환
        Member member = (Member) form.toEntity();
        log.info(member.toString());  // DTO가 엔티티로 잘 변환되는지 확인 출력

        // 2. 리파지터리로 엔티티를 DB에 저장
        Member saved = memberRepository.save(member);
        log.info(saved.toString());
        return "";
    }
}
