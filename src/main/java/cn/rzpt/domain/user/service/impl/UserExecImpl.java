package cn.rzpt.domain.user.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.rzpt.domain.role.repository.IRoleRepository;
import cn.rzpt.domain.support.model.event.BaseDomainEvent;
import cn.rzpt.domain.support.model.event.DomainEventPublisher;
import cn.rzpt.domain.user.model.req.UserLoginReq;
import cn.rzpt.domain.user.model.req.UserRegisterReq;
import cn.rzpt.domain.user.model.res.LoginResult;
import cn.rzpt.domain.user.service.IUserExec;
import cn.rzpt.domain.user.service.UserBase;
import cn.rzpt.domain.user.service.event.UserRoleListEvent;
import cn.rzpt.infrastructure.po.UserPO;
import cn.rzpt.infrastructure.properties.JwtProperties;
import cn.rzpt.infrastructure.repository.UserRepository;
import cn.rzpt.infrastructure.util.JwtUtil;
import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static cn.rzpt.common.Constants.RedisKey.LOGIN_USER_INFO;


@Service("userExec")
@RequiredArgsConstructor
public class UserExecImpl extends UserBase implements IUserExec {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private DomainEventPublisher domainEventPublisher;

    private final JwtProperties jwtProperties;
    private final IRoleRepository roleRepository;
    private final UserRepository userRepository;


    @Override
    public LoginResult login(UserLoginReq req) {
        UserPO userPO = userRepository.login(req);
        if (ObjectUtil.isEmpty(userPO)) {
            throw new RuntimeException("账号或密码错误");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("id", userPO.getId());
        redisTemplate.opsForValue().set(LOGIN_USER_INFO + userPO.getId(), JSON.toJSONString(userPO));
        return LoginResult.builder().token(JwtUtil.createJWT(jwtProperties.getSecret(), 64800L, map)).build();
    }

    @Override
    public void register(UserRegisterReq req) {
        // 注册用户
        Long userId = userRepository.register(req);
        // 用户关联默认用户角色
        domainEventPublisher.publishEvent(new UserRoleListEvent(userId,roleRepository));

    }
}
