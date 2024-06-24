package cn.rzpt.domain.user.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.rzpt.domain.user.model.req.UserLoginReq;
import cn.rzpt.domain.user.model.res.LoginResult;
import cn.rzpt.domain.user.model.vo.RegisterUserVO;
import cn.rzpt.domain.user.service.IUserExec;
import cn.rzpt.domain.user.service.UserBase;
import cn.rzpt.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("userExec")
@RequiredArgsConstructor
public class UserExecImpl extends UserBase implements IUserExec {

    private final UserRepository userRepository;

    @Override
    public LoginResult login(UserLoginReq req) {
        super.log.info("userExec:{}", req);
        RegisterUserVO registerUserVO = userRepository.login(req);
        if (ObjectUtil.isEmpty(registerUserVO)) {
            throw new RuntimeException("账号或密码错误");
        }
        //TODO 颁发令牌
        return LoginResult.builder().token("token").build();
    }
}
