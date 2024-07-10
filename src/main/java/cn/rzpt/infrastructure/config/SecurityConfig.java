package cn.rzpt.infrastructure.config;

import cn.rzpt.application.filter.AuthorizationFilter;
import cn.rzpt.application.handler.AccessDeniedHandler;
import cn.rzpt.application.handler.AuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Web安全配置
 *
 * @author Waxjava04
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthorizationFilter authorizationFilter;
    private final AccessDeniedHandler accessDeniedHandler;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AuthenticationConfiguration authenticationConfiguration;


    /**
     * 配置密码加密器
     *
     * @return BCryptPasswordEncoder密码加密器对象
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证管理器
     *
     * @return 认证管理器对象
     */
    @Bean
    @SneakyThrows
    public AuthenticationManager authenticationManager() {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @SneakyThrows
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) {
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.authorizeHttpRequests(request -> {
            request.requestMatchers("/user/login", "/user/register","/actuator/**").permitAll()
                    .anyRequest().authenticated();
        });
        httpSecurity.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.exceptionHandling(access -> access.accessDeniedHandler(accessDeniedHandler));
        httpSecurity.exceptionHandling(authenticationConfiguration -> authenticationConfiguration.authenticationEntryPoint(authenticationEntryPoint));
        httpSecurity.cors(AbstractHttpConfigurer::disable);
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }

}
