package com.wongakim.springstudy.api.member;

import com.wongakim.springstudy.schema.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {}
