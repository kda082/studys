package com.studys.sh.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//  엔티티클래스와 엔티티 레파지토리는 위치가 동일해야함,   JpaRepository<엔티티 클래스,pk타입>
public interface PostsRepository extends JpaRepository<Posts,Long> {
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC") // SpringDataJpa 에 없는 메서드를 쿼리로 작성가능
    List<Posts> findAllDesc();
}
