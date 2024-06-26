package cn.rzpt.domain.user.model.aggregates;

import cn.rzpt.domain.role.model.vo.RoleVO;
import cn.rzpt.domain.user.model.vo.UserInfoVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户角色聚合对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleAggregates {

    private UserInfoVO userInfoVO;
    private List<RoleVO> roleVOList;

}
