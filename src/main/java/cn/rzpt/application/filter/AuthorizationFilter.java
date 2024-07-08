package cn.rzpt.application.filter;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.rzpt.domain.user.model.aggregates.UserRoleAggregates;
import cn.rzpt.infrastructure.properties.JwtProperties;
import cn.rzpt.infrastructure.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import static cn.rzpt.common.Constants.RedisKey.LOGIN_USER_INFO;

/**
 * 认证过滤器
 */
@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {
    private final JwtProperties jwtProperties;
    private final RedisTemplate<String, String> redisTemplate;
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
        UserRoleAggregates userRoleAggregates = JSONUtil.toBean
                (redisTemplate.opsForValue().get(LOGIN_USER_INFO + request.getHeader(AUTHORIZATION)),
                        UserRoleAggregates.class);
        if (ObjUtil.isNull(userRoleAggregates)) {
            throw new RuntimeException("请先登录");
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userRoleAggregates, null, null);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request,response);
    }
}
