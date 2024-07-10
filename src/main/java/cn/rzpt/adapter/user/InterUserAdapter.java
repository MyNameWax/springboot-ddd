package cn.rzpt.adapter.user;

import cn.rzpt.common.Constants;
import cn.rzpt.common.Result;
import cn.rzpt.domain.user.model.req.UserLoginReq;
import cn.rzpt.domain.user.model.req.UserRegisterReq;
import cn.rzpt.domain.user.model.res.LoginResult;
import cn.rzpt.domain.user.model.vo.UserInfoVO;
import cn.rzpt.domain.user.repository.IUserRepository;
import cn.rzpt.domain.user.service.IUserExec;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class InterUserAdapter {

    private final IUserExec userExec;
    private final IUserRepository userRepository;


    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 统一对象返回
     */
    @GetMapping
    public Result getUserByUsername(String username) {
        UserInfoVO userInfoVO = userRepository.getUserByUsername(username);
        return Result.buildResult(Constants.ResponseCode.SUCCESS, userInfoVO);
    }

    /**
     * 用户登录接口
     *
     * @param userLoginReq 用户登录信息请求体
     * @return 统一结果返回
     */
    @PostMapping("/login")
    public Result login(@RequestBody UserLoginReq userLoginReq) {
        LoginResult login = userExec.login(userLoginReq);
        return Result.buildResult(Constants.ResponseCode.SUCCESS, login);
    }

    /**
     * 用户注册接口
     *
     * @param userRegisterReq 用户注册信息请求体
     * @return 统一结果返回
     */
    @PostMapping("/register")
    public Result register(@RequestBody UserRegisterReq userRegisterReq) {
        userExec.register(userRegisterReq);
        return Result.buildResult(Constants.ResponseCode.SUCCESS);
    }

}
