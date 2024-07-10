package cn.rzpt.domain.user.repository;

import cn.rzpt.domain.user.model.req.UserLoginReq;
import cn.rzpt.domain.user.model.req.UserRegisterReq;
import cn.rzpt.domain.user.model.vo.UserInfoVO;
import cn.rzpt.infrastructure.mybatis.po.UserPO;

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
    Long register(UserRegisterReq req);

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    UserInfoVO getUserByUsername(String username);


}
