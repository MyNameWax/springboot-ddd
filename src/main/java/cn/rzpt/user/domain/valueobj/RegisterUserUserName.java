package cn.rzpt.user.domain.valueobj;

import cn.hutool.core.util.StrUtil;
import lombok.Data;

@Data
public class RegisterUserUserName {


    /**
     * 用户名
     */
    private String username;

    /**
     * 用户名最短长度
     */
    public static final int MIN_USERNAME_LENGTH = 4;

    public RegisterUserUserName(String username) {
        username = username.trim();
        if (StrUtil.isEmpty(username)) {
            throw new RuntimeException("用户名不允许为空");
        } else if (StrUtil.length(username) < MIN_USERNAME_LENGTH) {
            throw new RuntimeException("用户名长度最少4位");
        }
        this.username = username;
    }
}
