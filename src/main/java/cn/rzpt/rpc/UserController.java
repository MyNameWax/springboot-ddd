package cn.rzpt.rpc;

import cn.rzpt.common.Constants;
import cn.rzpt.common.Result;
import cn.rzpt.domain.user.model.req.UserLoginReq;
import cn.rzpt.domain.user.model.res.LoginResult;
import cn.rzpt.domain.user.service.IUserExec;
import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final IUserExec userExec;

    @PostMapping("/login")
    public Result login(@RequestBody UserLoginReq userLoginReq) {
        LoginResult login = userExec.login(userLoginReq);
        return Result.buildResult(Constants.ResponseCode.SUCCESS, login);
    }

}
