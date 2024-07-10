package cn.rzpt.application.filter;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import cn.rzpt.domain.role.model.vo.RoleVO;
import cn.rzpt.domain.user.model.aggregates.UserRoleAggregates;
import cn.rzpt.domain.user.model.vo.UserInfoVO;
import cn.rzpt.infrastructure.properties.JwtProperties;
import cn.rzpt.infrastructure.util.JwtUtil;
import com.alibaba.fastjson.JSON;
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

import java.util.LinkedHashMap;
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
//        LinkedHashMap<String, Object> linkedHashMap = (LinkedHashMap<String, Object>) redisTemplate.opsForValue().get(LOGIN_USER_INFO + 26);
//        UserInfoVO userInfoVO = (UserInfoVO) linkedHashMap.get("userInfoVO");
//        RoleVO roleVO = (RoleVO) linkedHashMap.get("roleVO");
//        UserRoleAggregates userRoleAggregates = UserRoleAggregates.builder()
//                .userInfoVO(userInfoVO)
//                .roleVO(roleVO)
//                .build();
        String jsonData = Objects.requireNonNull(redisTemplate.opsForValue().get(LOGIN_USER_INFO + claims.get("id").toString())).toString();
        UserRoleAggregates userRoleAggregates = JSON.parseObject(jsonData, UserRoleAggregates.class);
        if (ObjUtil.isNull(userRoleAggregates.getUserInfoVO())) {
            throw new RuntimeException("请先登录");
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userRoleAggregates , null, null);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);
    }
}
