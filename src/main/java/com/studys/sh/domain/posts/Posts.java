package com.studys.sh.domain.posts;


import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//기본적인 구조는 생성자를 통해 db에 insert를 하는 것이므로 Setter가 필요없음

@Getter
@NoArgsConstructor
@Entity //db 테이블과 링크될 클래스
public class Posts extends BaseTimeEntity {
@Id  //pk 필드를 나타냄
@GeneratedValue(strategy = GenerationType.IDENTITY) // pk의 생성 규칙임 , GenerationType.IDENTITY 는 시퀀스 기능임
private Long id;

@Column(length = 500, nullable = false) // @column이 없어도 이 클래스의 모든 필드는 컬럼이되지만 기본 값외에 추가로 변경할 일이 있을때..
private String title;

@Column(columnDefinition = "TEXT" , nullable = false)
private String content;
private String author;

@Builder  //bulid 형식의 클래스 생성을 위해 (생성자 대신 사용)
public Posts(String title,String content,String author){
    this.title = title;
    this.content = content;
    this.author = author;
}


public void update(String title, String content) {
    this.title = title;
    this.content = content;
}


}
