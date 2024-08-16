package com.example.springstudy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity  // 엔티티 선언
@Getter
public class Article {
    @Id  // 엔티티의 대표값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동 생성 기능 추가(숫자가 자동으로 매겨짐)
    private Long id;
    @Column  // title 필드 선언, DB테이블의 title 열과 연결됨
    private String title;
    @Column  // content 필드 선언, DB테이블의 content 열과 연결됨
    private String content;
}
