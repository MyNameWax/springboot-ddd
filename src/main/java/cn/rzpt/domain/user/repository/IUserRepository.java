package cn.rzpt.domain.user.repository;

import cn.rzpt.domain.user.model.req.UserLoginReq;
import cn.rzpt.domain.user.model.req.UserRegisterReq;
import cn.rzpt.infrastructure.po.UserPO;

public interface IUserRepository {

    /**
     * 用户登录
     *
     * @param req 用户登录信息请求体
     * @return LoginUserVO对象
     */
    UserPO login(UserLoginReq req);

    /**
     * 用户注册
     *
     * @param req 用户注册信息请求体
     */
    void register(UserRegisterReq req);


}
