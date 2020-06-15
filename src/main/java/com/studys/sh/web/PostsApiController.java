package com.studys.sh.web;


import com.studys.sh.service.posts.PostsService;
import com.studys.sh.web.dto.PostsResponseDto;
import com.studys.sh.web.dto.PostsSaveRequestDto;
import com.studys.sh.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor // 초기화 되지 않은 final 필드나 not null이 붙은 필드에 대해 생성자를 생성해줌
@RestController // 문자열과 json 전송 가능 @ResponseBody 역할
public class PostsApiController {

    private final PostsService postsService;

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findyId(@PathVariable Long id){
        return postsService.findById(id);
    }


    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto)
    {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id,requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }



}


