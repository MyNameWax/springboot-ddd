package cn.rzpt.user.application.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.rzpt.framework.core.domain.CommandHandler;
import cn.rzpt.role.application.res.RoleResp;
import cn.rzpt.role.domain.repository.IRoleRepository;
import cn.rzpt.framework.base.event.DomainEventPublisher;
import cn.rzpt.user.application.req.UserLoginReq;
import cn.rzpt.user.application.req.UserRegisterReq;
import cn.rzpt.user.application.res.LoginResultResp;
import cn.rzpt.user.application.res.UserInfoResp;
import cn.rzpt.user.domain.aggregates.UserRoleAggregates;
import cn.rzpt.user.domain.repository.IUserRoleRepository;
import cn.rzpt.user.application.service.IUserService;
import cn.rzpt.user.application.service.UserBase;
import cn.rzpt.user.application.event.UserRoleListEvent;
import cn.rzpt.user.infrastructure.dao.entity.UserPO;
import cn.rzpt.framework.base.properties.JwtProperties;
import cn.rzpt.user.infrastructure.repository.UserRepository;
import cn.rzpt.framework.common.toolkit.JwtUtil;
import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static cn.rzpt.framework.web.result.Constants.RedisKey.LOGIN_USER_INFO;


@Service
@RequiredArgsConstructor
public class UserServiceImpl extends UserBase implements IUserService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private DomainEventPublisher domainEventPublisher;
    private final JwtProperties jwtProperties;
    private final IRoleRepository roleRepository;
    private final UserRepository userRepository;
    private final CommandHandler<UserRegisterReq,Long> userRegisterCommandHandler;
    private final IUserRoleRepository userRoleRepository;
    private final AuthenticationManager authenticationManager;


    @Override
    public LoginResultResp login(UserLoginReq req) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (ObjUtil.isNull(authenticate)) {
            throw new RuntimeException("账号或密码错误");
        }
        UserPO userPO = (UserPO) authenticate.getPrincipal();
        // 用户信息转换
        UserInfoResp userInfoVO = BeanUtil.copyProperties(userPO, UserInfoResp.class);
        userInfoVO.setUserPO(userPO);
        // 获取角色信息
        RoleResp roleVo = userRoleRepository.getRoleById(userPO.getId());
        // 构建聚合
        UserRoleAggregates userRoleAggregates = UserRoleAggregates
                .builder()
                .userInfoVO(userInfoVO)
                .roleVO(roleVo)
                .build();
        if (ObjectUtil.isEmpty(userPO)) {
            throw new RuntimeException("账号或密码错误");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("id", userPO.getId());
        redisTemplate.opsForValue().set(LOGIN_USER_INFO + userPO.getId(), JSON.toJSONString(userRoleAggregates));
        return LoginResultResp.builder().token(JwtUtil.createJWT(jwtProperties.getSecret(), 604800000L, map)).build();
    }

    @Override
    public void register(UserRegisterReq req) {
        Long userId = userRegisterCommandHandler.handler(req);
        domainEventPublisher.publishEvent(new UserRoleListEvent(userId, roleRepository));

    }

    @Override
    public UserInfoResp getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }
}
