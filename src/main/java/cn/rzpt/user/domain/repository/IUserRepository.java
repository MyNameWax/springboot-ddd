package cn.rzpt.user.domain.repository;

import cn.rzpt.user.application.req.UserLoginReq;
import cn.rzpt.user.application.req.UserRegisterReq;
import cn.rzpt.user.application.res.UserInfoResp;
import cn.rzpt.user.infrastructure.dao.entity.UserPO;

public interface IUserRepository {

    /**
     * 用户登录
     *
     * @param req 用户登录信息请求体
     * @return LoginUserVO对象
     */
    UserPO login(UserLoginReq req);

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    UserInfoResp getUserByUsername(String username);


}
