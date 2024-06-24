package cn.rzpt.infrastructure.repository;

import cn.rzpt.domain.user.model.req.UserLoginReq;
import cn.rzpt.domain.user.model.vo.RegisterUserVO;
import cn.rzpt.domain.user.repository.IUserRepository;
import cn.rzpt.infrastructure.mapper.UserMapper;
import cn.rzpt.infrastructure.po.UserPO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 用户表仓储对象
 */
@Component
@RequiredArgsConstructor
public class UserRepository implements IUserRepository {

    private final UserMapper userMapper;

    @Override
    public RegisterUserVO login(UserLoginReq req) {
        LambdaQueryWrapper<UserPO> queryWrapper = Wrappers.lambdaQuery(UserPO.class)
                .eq(UserPO::getUsername, req.getUsername())
                .eq(UserPO::getPassword, req.getPassword());
        UserPO userPO = userMapper.selectOne(queryWrapper);
        return RegisterUserVO.builder()
                .username(userPO.getUsername())
                .avatar(userPO.getAvatar())
                .address(userPO.getAddress())
                .email(userPO.getEmail())
                .build();
    }
}
