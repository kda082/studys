package com.studys.sh.web.dto;

import com.studys.sh.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//Entity 클래스와 거의 유사한 형태이지만
//Entity 클래스를 Request/Response 클래스로 사용해선 안된다.
// Entity 클래스는 데이터베이스와 맣닿은 클래스이기때문에 이 클래스를 기준으로 테이블이 생성되고 스키마가 변경된다.
@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title,String content,String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
