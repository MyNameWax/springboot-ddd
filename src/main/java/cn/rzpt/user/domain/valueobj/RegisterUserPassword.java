package cn.rzpt.user.domain.valueobj;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
public class RegisterUserPassword {

    private String password;

    public static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static final int MIN_PASSWORD_LENGTH = 6;

    public RegisterUserPassword(String password) {
        password = password.trim();
        if (password.isEmpty()) {
            throw new RuntimeException("密码不允许为空");
        } else if (StrUtil.length(password) < MIN_PASSWORD_LENGTH) {
            throw new RuntimeException("密码长度最少6位");
        }
        this.password = encoder.encode(password);
    }

}
