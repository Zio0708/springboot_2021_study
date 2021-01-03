package com.springboot_2020.study.config.auth.dto;

import com.springboot_2020.study.domain.user.Role;
import com.springboot_2020.study.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes (Map<String, Object> attributes, String nameAttributeKey, String name, String email,
                           String picture){
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }
    public static OAuthAttributes of(String registrationId ,String userNAmeAttributeName,
                                      Map<String, Object> attributes){
        return ofGoogle(userNAmeAttributeName,attributes);
    }//느낌상 구글 네이버등 담기위해서 만든거 같긴한데..?왜? 잘모르겠음 솔직히

    public static OAuthAttributes ofGoogle(String userNameAttributeName , Map<String, Object> attributes){
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }  //빌더에 담기위해서 map받고 일일히 분해해서 넣는거다
    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }//User엔티티를 만든다 -> OAuthAttributes 에서 엔티티를 만드는 시점은 처음 가입때 그래서 초기설정은 GUEST
}
