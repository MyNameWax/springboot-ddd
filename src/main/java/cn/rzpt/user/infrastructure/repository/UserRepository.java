package cn.rzpt.user.infrastructure.repository;

import cn.rzpt.user.application.req.UserLoginReq;
import cn.rzpt.user.application.req.UserRegisterReq;
import cn.rzpt.user.application.res.UserInfoResp;
import cn.rzpt.user.domain.repository.IUserRepository;
import cn.rzpt.user.infrastructure.dao.mapper.UserMapper;
import cn.rzpt.user.infrastructure.dao.entity.UserPO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 用户表仓储对象
 *
 * @author waxja
 */
@Component
@RequiredArgsConstructor
public class UserRepository implements IUserRepository {

    private final UserMapper userMapper;

    /**
     * 用户登录
     *
     * @param req 用户登录信息请求体
     * @return UserPO对象
     */
    @Override
    public UserPO login(UserLoginReq req) {
        checkUserLoginParams(req);
        LambdaQueryWrapper<UserPO> queryWrapper = Wrappers.lambdaQuery(UserPO.class)
                .eq(UserPO::getUsername, req.getUsername())
                .eq(UserPO::getPassword, req.getPassword());
        return userMapper.selectOne(queryWrapper);
    }


    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息VO对象
     */
    @Override
    public UserInfoResp getUserByUsername(String username) {
        LambdaQueryWrapper<UserPO> queryWrapper = Wrappers.lambdaQuery(UserPO.class)
                .eq(UserPO::getUsername, username);
        UserPO userPO = userMapper.selectOne(queryWrapper);
        if (userPO == null) {
            return null;
        }
        return UserInfoResp.builder()
                .address(userPO.getAddress())
                .email(userPO.getEmail())
                .id(userPO.getId())
                .username(userPO.getUsername())
                .avatar(userPO.getAvatar())
                .build();
    }

    private void checkUserRegisterParams(UserRegisterReq req) {
        Assert.notEmpty(req.getUsername(), "用户名不能为空");
        Assert.notEmpty(req.getPassword(), "密码不能为空");
    }

    private void checkUserLoginParams(UserLoginReq req) {
        Assert.notEmpty(req.getUsername(), "用户名不能为空");
        Assert.notEmpty(req.getPassword(), "密码不能为空");
    }
}
