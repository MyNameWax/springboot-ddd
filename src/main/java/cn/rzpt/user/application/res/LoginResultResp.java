package cn.rzpt.user.application.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResultResp {

    /**
     * 用户登录Token令牌
     */
    private String token;

}
