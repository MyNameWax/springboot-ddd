package cn.rzpt.user.interfaces;

import cn.rzpt.framework.web.result.Constants;
import cn.rzpt.framework.web.result.Result;
import cn.rzpt.user.application.req.UserLoginReq;
import cn.rzpt.user.application.req.UserRegisterReq;
import cn.rzpt.user.application.res.LoginResultResp;
import cn.rzpt.user.application.res.UserInfoResp;
import cn.rzpt.user.domain.repository.IUserRepository;
import cn.rzpt.user.application.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;


    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 统一对象返回
     */
    @GetMapping
    public Result getUserByUsername(String username) {
        UserInfoResp userInfoVO = userService.getUserByUsername(username);
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
        LoginResultResp login = userService.login(userLoginReq);
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
        userService.register(userRegisterReq);
        return Result.buildResult(Constants.ResponseCode.SUCCESS);
    }

}
