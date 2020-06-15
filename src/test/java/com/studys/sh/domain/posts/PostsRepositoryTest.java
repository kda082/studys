package com.studys.sh.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

//여기서 테스트는 PostsRepository를 가져온 PostsService를 가정하는것

@RunWith(SpringRunner.class)
@SpringBootTest // h2 db가 자동으로 실행됨
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;
    
    @After  //테스트가 끝난 후 수행되는 메서드
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void board_save(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(   //테이블 posts에 insert/update 쿼리를 실행한다. (id값이 있다면 update,없다면 insert)
                Posts.builder()
                .title(title)
                .content(content)
                .author("kda082@gmail.com")
                .build());

        //when  // 테이블 posts에 모든 데이터를 조회해오는 메서드
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
    @Test
    public void BaseTimeEntity_insert(){
        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        
        System.out.println(">>>>>> createDate=" + posts.getCreatedDate()+",modifiedDate=" + posts.getModifiedDate());

        //.isAfter (날짜 비교메서드 현재날짜가 명시된 객체보다 늦은시간인지)
        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }

}
