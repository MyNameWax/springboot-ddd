package cn.rzpt.framework.base.config.security;

import cn.rzpt.framework.common.filter.AuthorizationFilter;
import cn.rzpt.framework.common.handler.AccessDeniedHandlerFilter;
import cn.rzpt.framework.common.handler.AuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
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
@EnableMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class SecurityConfig {

    private final AuthorizationFilter authorizationFilter;
    private final AccessDeniedHandlerFilter accessDeniedHandlerFilter;
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
        // 禁用session管理器
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // 配置权限
        httpSecurity.authorizeHttpRequests(request -> {
            request.requestMatchers("/user/login", "/user/register", "/actuator/**").permitAll()
                    .anyRequest().authenticated();
        });
        // 添加过滤器
        httpSecurity.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.exceptionHandling(accessDeniedHandler -> accessDeniedHandler.accessDeniedHandler(accessDeniedHandlerFilter));
        httpSecurity.exceptionHandling(authenticationConfiguration -> authenticationConfiguration.authenticationEntryPoint(authenticationEntryPoint));
        httpSecurity.cors(AbstractHttpConfigurer::disable);
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }

}
