package com.springboot_2020.study.web;

import com.springboot_2020.study.config.auth.LoginUser;
import com.springboot_2020.study.config.auth.dto.SessionUser;
import com.springboot_2020.study.service.posts.PostsService;
import com.springboot_2020.study.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession; //세션에 저장한다는 뜻이겠지..? 오랜만이라..??!?

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts",postsService.findAllDesc());
        //SessionUser user  = (SessionUser) httpSession.getAttribute("user");세션에 로그인되면 유저 가져오기 가능
        if(user != null){
            model.addAttribute("userName", user.getName());//없으면 로그인 버튼 노출
        }
        return "index";
    }
    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id , Model model){
        PostsResponseDto dto=  postsService.findById(id);
        model.addAttribute("post",dto);
        return "posts-update";
    }
}
