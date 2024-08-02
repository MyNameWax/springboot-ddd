package cn.rzpt.user.application.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterReq {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;



}
