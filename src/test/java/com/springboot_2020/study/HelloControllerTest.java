package com.springboot_2020.study;
import com.springboot_2020.study.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
//같이 돌린다는 뜻임 뭐랑? SpringRunner랑
@WebMvcTest(controllers = HelloController.class)
//Web Spring MVC 스타일에 집중된 테스트 코드 Controller 같은 거 테스트 하기 좋다
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc;
    //스프링이 관리중인 빈을 주입받는다
    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount =1000;
        mvc.perform(
                get("/hello/dto")
                        .param("name",name)//String으로만 param설정해주는 코드
                        .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())//전체 테스트용
                .andExpect(jsonPath("$.name",is(name)))//각 param 지정 검사
                .andExpect(jsonPath("$.amount",is(amount)));

    }
}
