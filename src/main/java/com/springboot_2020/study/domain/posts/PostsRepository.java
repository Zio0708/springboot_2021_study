package com.springboot_2020.study.domain.posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long>{//Entity 클래스와 ,PK 타입을 넣어주자
    //암거도 안만드냐? ㅇㅇ 안만든다 왜? 알아서 CRUD를 만들어준다.

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
