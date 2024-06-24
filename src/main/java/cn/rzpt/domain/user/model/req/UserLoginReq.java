package cn.rzpt.domain.user.model.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginReq {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;


}
