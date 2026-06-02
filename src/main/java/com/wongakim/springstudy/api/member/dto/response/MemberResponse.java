package com.wongakim.springstudy.api.member.dto.response;

import com.wongakim.springstudy.schema.Member;

public record MemberResponse(Long id, String email) {
    public static MemberResponse from(Member member) {
        return new MemberResponse(member.getId(), member.getEmail());
    }
}
