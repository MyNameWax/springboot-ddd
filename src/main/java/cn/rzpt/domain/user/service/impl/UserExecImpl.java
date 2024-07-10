package cn.rzpt.domain.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.rzpt.domain.role.model.vo.RoleVO;
import cn.rzpt.domain.role.repository.IRoleRepository;
import cn.rzpt.domain.support.model.event.DomainEventPublisher;
import cn.rzpt.domain.user.model.aggregates.UserRoleAggregates;
import cn.rzpt.domain.user.model.req.UserLoginReq;
import cn.rzpt.domain.user.model.req.UserRegisterReq;
import cn.rzpt.domain.user.model.res.LoginResult;
import cn.rzpt.domain.user.model.vo.UserInfoVO;
import cn.rzpt.domain.user.repository.IUserRoleRepository;
import cn.rzpt.domain.user.service.IUserExec;
import cn.rzpt.domain.user.service.UserBase;
import cn.rzpt.domain.user.service.event.UserRoleListEvent;
import cn.rzpt.infrastructure.mybatis.po.UserPO;
import cn.rzpt.infrastructure.properties.JwtProperties;
import cn.rzpt.infrastructure.repository.UserRepository;
import cn.rzpt.infrastructure.util.JwtUtil;
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

import static cn.rzpt.common.Constants.RedisKey.LOGIN_USER_INFO;


@Service("userExec")
@RequiredArgsConstructor
public class UserExecImpl extends UserBase implements IUserExec {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private DomainEventPublisher domainEventPublisher;
    private final JwtProperties jwtProperties;
    private final IRoleRepository roleRepository;
    private final UserRepository userRepository;
    private final IUserRoleRepository userRoleRepository;
    private final AuthenticationManager authenticationManager;


    @Override
    public LoginResult login(UserLoginReq req) {
        // 用户登录
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (ObjUtil.isNull(authenticate)) {
            throw new RuntimeException("账号或密码错误");
        }
        UserPO userPO = (UserPO) authenticate.getPrincipal();
        // 用户信息转换
        UserInfoVO userInfoVO = BeanUtil.copyProperties(userPO, UserInfoVO.class);
        // 获取角色信息
        RoleVO roleVo = userRoleRepository.getRoleById(userPO.getId());
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
        return LoginResult.builder().token(JwtUtil.createJWT(jwtProperties.getSecret(), 604800000L, map)).role(roleVo).build();
    }

    @Override
    public void register(UserRegisterReq req) {
        // 注册用户
        Long userId = userRepository.register(req);
        // 用户关联默认用户角色
        domainEventPublisher.publishEvent(new UserRoleListEvent(userId, roleRepository));

    }
}
