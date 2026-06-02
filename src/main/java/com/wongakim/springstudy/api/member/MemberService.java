package com.wongakim.springstudy.api.member;

import com.wongakim.springstudy.api.member.dto.request.MemberRequest;
import com.wongakim.springstudy.api.member.dto.response.MemberResponse;
import com.wongakim.springstudy.schema.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public List<MemberResponse> findAll() {
        return StreamSupport.stream(memberRepository.findAll().spliterator(), false)
                .map(MemberResponse::from)
                .toList();
    }

    public MemberResponse findById(Long id) {
        return memberRepository.findById(id)
                .map(MemberResponse::from)
                .orElse(null);
    }

    public MemberResponse join(MemberRequest request) {
        Member member = new Member(null, request.email(), request.password());
        return MemberResponse.from(memberRepository.save(member));
    }
}
