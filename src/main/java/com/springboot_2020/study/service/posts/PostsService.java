package com.springboot_2020.study.service.posts;

import com.springboot_2020.study.domain.posts.Posts;
import com.springboot_2020.study.domain.posts.PostsRepository;
import com.springboot_2020.study.web.dto.PostsResponseDto;
import com.springboot_2020.study.web.dto.PostsSaveRequestDto;
import com.springboot_2020.study.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntitiy()).getId();
    }

    @Transactional
    public Long update(Long id ,PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("게시글이 없습니다. id ="+ id ));
        posts.update(requestDto.getTitle(),requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글 없음. id="+ id));
        return new PostsResponseDto(entity);
    }
}
