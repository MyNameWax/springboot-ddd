package cn.rzpt.user.domain.aggregates;

import cn.rzpt.role.application.res.RoleResp;
import cn.rzpt.user.application.res.UserInfoResp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户角色聚合对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleAggregates {

    private UserInfoResp userInfoVO;
    private RoleResp roleVO;

}
