package cn.rzpt.user.infrastructure.repository;


import cn.rzpt.role.application.res.RoleResp;
import cn.rzpt.user.domain.repository.IUserRoleRepository;
import cn.rzpt.user.infrastructure.dao.mapper.RoleMapper;
import cn.rzpt.user.infrastructure.dao.mapper.UserRoleMapper;
import cn.rzpt.user.infrastructure.dao.entity.RolePO;
import cn.rzpt.user.infrastructure.dao.entity.UserRolePO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 用户角色仓储对象
 */
@Component
@RequiredArgsConstructor
public class UserRoleRepository implements IUserRoleRepository {

    private final RoleMapper roleMapper;
    private final UserRoleMapper userRoleMapper;

    @Override
    public RoleResp getRoleById(Long id) {
        LambdaQueryWrapper<UserRolePO> queryWrapper = Wrappers.lambdaQuery(UserRolePO.class).eq(UserRolePO::getUId, id);
        UserRolePO userRolePO = userRoleMapper.selectOne(queryWrapper);
        if (userRolePO == null) {
            return null;
        }
        Long rId = userRolePO.getRId();
        RolePO rolePO = roleMapper.selectById(rId);
        return RoleResp.builder()
                .code(rolePO.getCode())
                .name(rolePO.getName())
                .desc(rolePO.getDesc())
                .build();
    }
}
