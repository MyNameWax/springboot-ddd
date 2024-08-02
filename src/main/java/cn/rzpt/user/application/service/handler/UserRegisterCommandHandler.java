package cn.rzpt.user.application.service.handler;

import cn.rzpt.framework.core.domain.CommandHandler;
import cn.rzpt.user.application.req.UserRegisterReq;
import cn.rzpt.user.domain.aggregates.RegisterUser;
import cn.rzpt.user.domain.valueobj.RegisterUserPassword;
import cn.rzpt.user.domain.valueobj.RegisterUserUserName;
import cn.rzpt.user.infrastructure.dao.entity.UserPO;
import cn.rzpt.user.infrastructure.dao.mapper.UserMapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserRegisterCommandHandler implements CommandHandler<UserRegisterReq, Long> {

    @Resource
    private UserMapper userMapper;

    @Override
    public Long handler(UserRegisterReq req) {
        RegisterUser registerUser = RegisterUser.builder().username(new RegisterUserUserName(req.getUsername())).password(new RegisterUserPassword(req.getPassword())).build();
        Assert.isNull(userMapper.selectOne(Wrappers.lambdaQuery(UserPO.class)
                .eq(UserPO::getUsername, registerUser.getUsername().getUsername())), "用户名已存在");
        // 构建对象
        UserPO userPO = UserPO.builder()
                .username(req.getUsername())
                .password(registerUser.getPassword().getPassword())
                .build();
        userMapper.insert(userPO);
        return userPO.getId();
    }
}
