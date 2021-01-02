package com.springboot_2020.study.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity//테이블과 링크함
public class Posts extends BaseTimeEntity{

    @Id//테이블의 pk필드
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//엔간하면 long 타입 자동증가를 쓰자

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition ="TEXT" , nullable = false)
    private String content;

    private String author;

    //왜쓰냐? - 생성자인 경우 실수해도 확인하기 어려움, 빌더 패턴으로 바로바로 알자!
    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author =author;
    }
    public void update(String title, String content){
        this.title= title;
        this.content = content;
    }
}
