package cn.rzpt.user.domain.aggregates;

import cn.rzpt.user.domain.valueobj.RegisterUserPassword;
import cn.rzpt.user.domain.valueobj.RegisterUserUserName;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class RegisterUser {

    private RegisterUserUserName username;
    private RegisterUserPassword password;

}
