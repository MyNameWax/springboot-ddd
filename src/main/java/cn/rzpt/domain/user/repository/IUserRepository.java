package cn.rzpt.domain.user.repository;

import cn.rzpt.domain.user.model.req.UserLoginReq;
import cn.rzpt.domain.user.model.vo.RegisterUserVO;

public interface IUserRepository {

    /**
     * 用户登录
     *
     * @param req 用户登录信息请求体
     * @return LoginUserVO对象
     */
    RegisterUserVO login(UserLoginReq req);


}
