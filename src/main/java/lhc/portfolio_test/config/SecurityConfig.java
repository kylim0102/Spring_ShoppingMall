package lhc.portfolio_test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");
        return hierarchy;
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, authentication) -> {
            response.sendRedirect("/"); // 로그아웃 성공 시 메인 홈페이지로 리다이렉트
        };
    }

    @Bean
    public LogoutHandler logoutHandler() {
        return (request, response, authentication) -> {
            // 로그아웃 처리 작업 추가 (예: 로그 기록 등)
        };
    }


    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login", "/signup", "/signupProc", "/img/**", "/inc/**", "/js/**", "/idCheck",
                                "/css/**", "/category/**", "/cart", "/api/**", "/upload/**", "/product/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasRole("USER")
                        .anyRequest().authenticated()
                );

        http
                .formLogin((auth) -> auth
                        .loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .usernameParameter("id")
                        .permitAll()
                );

        http
                .logout(configurer -> configurer
                        .logoutUrl("/logout")
                        .logoutSuccessHandler(logoutSuccessHandler())
                        .addLogoutHandler(logoutHandler())
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );

        http
                .csrf((auth) -> auth.disable())
                .cors((auth) -> auth.disable());

        http
                .sessionManagement((auth) -> auth
                .maximumSessions(1)                       //동시 접속 로그인 가능 수
                .maxSessionsPreventsLogin(true));         //값 초과시 기존 로그인 로그아웃 시킬지 새로 로그인을 강제 시킬지

        http
                .sessionManagement((auth) -> auth
                .sessionFixation().changeSessionId());

        return http.build();
    }


}