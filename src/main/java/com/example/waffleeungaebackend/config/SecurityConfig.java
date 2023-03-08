package com.example.waffleeungaebackend.config;


import com.example.waffleeungaebackend.entity.Role;
import com.example.waffleeungaebackend.service.CustomOAuth2MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    private final CustomOAuth2MemberService customOAuth2MemberService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http
                .csrf().disable()//csrf 공격을 막아주는 옵션을 disalbe, rest api같은 경우에는 브라우저를 통해 request 받지 않기 때문에 해당 옵션을 꺼도 됩니다.
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests(authorize -> authorize
                        .antMatchers("/","/css/**","/js/**","h2-console/**","/profile").permitAll()
                        .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                        .anyRequest().authenticated())
                .logout(logout -> logout
                        .logoutSuccessUrl("/")) //logout 요청시 홈으로 이동 - 기본 logout url = "/logout"
                .oauth2Login(oauth2Login -> oauth2Login //OAuth2 로그인 설정 시작점
                        .userInfoEndpoint() //OAuth2 로그인 성공 이후 사용자 정보를 가져올 때 설정 담당
                        .userService(customOAuth2MemberService)); //OAuth2 로그인 성공 시, 작업을 진행할 MemberService
        return http.build();
    }
}
