package cn.rzpt.user.application.service;

import cn.rzpt.user.application.req.UserLoginReq;
import cn.rzpt.user.application.req.UserRegisterReq;
import cn.rzpt.user.application.res.LoginResultResp;
import cn.rzpt.user.application.res.UserInfoResp;

public interface IUserService {
    /**
     * 用户登录
     *
     * @param req 用户登录信息请求体
     * @return LoginUserVO对象
     */
    LoginResultResp login(UserLoginReq req);

    /**
     * 用户注册
     *
     * @param req 用户注册信息请求体
     */
    void register(UserRegisterReq req);

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息对象
     */
    UserInfoResp getUserByUsername(String username);
}
