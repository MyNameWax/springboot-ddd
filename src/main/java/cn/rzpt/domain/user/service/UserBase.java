package cn.rzpt.domain.user.service;

import cn.rzpt.domain.user.model.req.UserLoginReq;
import cn.rzpt.domain.user.model.res.RegisterResult;
import cn.rzpt.domain.user.repository.IUserRepository;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserBase {
    protected Logger log = LoggerFactory.getLogger(UserBase.class);

    @Resource
    private IUserRepository userRepository;

}
