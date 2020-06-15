package com.studys.sh.service.posts;

import com.studys.sh.domain.posts.Posts;
import com.studys.sh.domain.posts.PostsRepository;
import com.studys.sh.web.dto.PostsListResponseDto;
import com.studys.sh.web.dto.PostsResponseDto;
import com.studys.sh.web.dto.PostsSaveRequestDto;
import com.studys.sh.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor //
@Service
public class PostsService {
    private final PostsRepository postsRepository; //@RequiredArgsConstructor로 인해 생성자로
    //bean을 주입받게 되었다.


    @Transactional //처리도중 에러가 났을때 롤백
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional   //entity 객체의 값만 변경하면 업데이트 쿼리가 필요없다. 1.더티체킹 트랜잭션 시작 2.엔티티 조회 3.엔티티의 값 변경 4. 트랜잭션 커밋 @DynamicUpdate를 쓰면 변경된 것만 대응
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다.id = " + id ));
        posts.update(requestDto.getTitle(),requestDto.getContent());
        return id;
    }

    @Transactional   //entity 객체의 값만 변경하면 업데이트 쿼리가 필요없다. 1.더티체킹 트랜잭션 시작 2.엔티티 조회 3.엔티티의 값 변경 4. 트랜잭션 커밋 @DynamicUpdate를 쓰면 변경된 것만 대응
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다.id = " + id ));
        postsRepository.delete(posts);
    }

    public PostsResponseDto findById (Long id) {
        //여기서 사용되는 findById는 내가 만든 메서드가 아님..
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "해당 사용자가 없습니다. id=" + id ));
        return new PostsResponseDto(entity);
        }

     @Transactional(readOnly = true)
     public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                //이 코드는  실제로는
                // .map(posts -> new PostsListResponseDto(posts))
                // 이 코드이다.
                // postsRepository 결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDto 변환 - > list로 반환하는 메서드
                .collect(Collectors.toList());
     }



}
