package cn.rzpt.user.application.service;

import cn.rzpt.user.domain.repository.IUserRepository;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserBase {
    protected Logger log = LoggerFactory.getLogger(UserBase.class);

    @Resource
    private IUserRepository userRepository;

}
