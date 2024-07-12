package cn.rzpt.infrastructure.repository;

import cn.rzpt.domain.user.model.req.UserLoginReq;
import cn.rzpt.domain.user.model.req.UserRegisterReq;
import cn.rzpt.domain.user.model.vo.UserInfoVO;
import cn.rzpt.domain.user.repository.IUserRepository;
import cn.rzpt.infrastructure.mybatis.mapper.UserMapper;
import cn.rzpt.infrastructure.mybatis.po.UserPO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 用户表仓储对象
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
     * 用户注册
     *
     * @param req 用户注册信息请求体
     */
    @Override
    public Long register(UserRegisterReq req) {
        checkUserRegisterParams(req);
        Assert.isNull(userMapper.selectOne(Wrappers.lambdaQuery(UserPO.class)
                .eq(UserPO::getUsername, req.getUsername())), "用户名已存在");
        UserPO userPO = UserPO.builder()
                .username(req.getUsername())
                .password(generatorPassword(req.getPassword()))
                .build();
        userMapper.insert(userPO);
        return userPO.getId();
    }

    /**
     * 生成加密后密码
     *
     * @param password 明文密码
     * @return 密文密码
     */
    private String generatorPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public UserInfoVO getUserByUsername(String username) {
        LambdaQueryWrapper<UserPO> queryWrapper = Wrappers.lambdaQuery(UserPO.class)
                .eq(UserPO::getUsername, username);
        UserPO userPO = userMapper.selectOne(queryWrapper);
        if (userPO == null) {
            return null;
        }
        return UserInfoVO.builder()
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
