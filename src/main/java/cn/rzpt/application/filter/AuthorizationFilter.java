package cn.rzpt.application.filter;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.rzpt.domain.user.model.aggregates.UserRoleAggregates;
import cn.rzpt.infrastructure.properties.JwtProperties;
import cn.rzpt.infrastructure.util.JwtUtil;
import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Objects;

import static cn.rzpt.common.Constants.RedisKey.LOGIN_USER_INFO;

/**
 * 认证过滤器
 */
@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {

    private final JwtProperties jwtProperties;
    private final RedisTemplate<String, Object> redisTemplate;
    public static final String AUTHORIZATION = "Authorization";

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        if (StrUtil.isBlank(request.getHeader(AUTHORIZATION))) {
            filterChain.doFilter(request, response);
            return;
        }
        Claims claims = JwtUtil.parseJWT(jwtProperties.getSecret(), request.getHeader(AUTHORIZATION));
        if (StrUtil.isEmpty(claims.get("id").toString())) {
            throw new RuntimeException("请先登录");
        }
        String jsonData = Objects.requireNonNull(redisTemplate.opsForValue().get(LOGIN_USER_INFO + claims.get("id").toString())).toString();
        UserRoleAggregates userRoleAggregates = JSONUtil.toBean(jsonData, UserRoleAggregates.class);
        if (ObjUtil.isNull(userRoleAggregates.getUserInfoVO())) {
            throw new RuntimeException("请先登录");
        }
        /**
         * Arg1: 登录的用户信息
         * Arg2: 密码(因为已经登录过了,所以直接null即可)
         * Arg3: 权限信息
         */
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userRoleAggregates, null, userRoleAggregates.getUserInfoVO().getUserPO().getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);
    }
}
