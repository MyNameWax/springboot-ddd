package cn.rzpt.domain.user.service;

import cn.rzpt.domain.user.model.req.UserLoginReq;
import cn.rzpt.domain.user.model.res.LoginResult;

public interface IUserExec {
    /**
     * 用户登录
     *
     * @param req 用户登录信息请求体
     * @return LoginUserVO对象
     */
    LoginResult login(UserLoginReq req);
}
