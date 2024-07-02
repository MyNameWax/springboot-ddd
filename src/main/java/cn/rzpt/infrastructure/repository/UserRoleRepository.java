package cn.rzpt.infrastructure.repository;


import cn.rzpt.domain.role.model.vo.RoleVO;
import cn.rzpt.domain.user.repository.IUserRoleRepository;
import cn.rzpt.infrastructure.mapper.RoleMapper;
import cn.rzpt.infrastructure.mapper.UserRoleMapper;
import cn.rzpt.infrastructure.po.RolePO;
import cn.rzpt.infrastructure.po.UserRolePO;
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
    public RoleVO getRoleById(Long id) {
        LambdaQueryWrapper<UserRolePO> queryWrapper = Wrappers.lambdaQuery(UserRolePO.class).eq(UserRolePO::getUId, id);
        UserRolePO userRolePO = userRoleMapper.selectOne(queryWrapper);
        if (userRolePO == null) {
            return null;
        }
        Long rId = userRolePO.getRId();
        RolePO rolePO = roleMapper.selectById(rId);
        return RoleVO.builder()
                .code(rolePO.getCode())
                .name(rolePO.getName())
                .desc(rolePO.getDesc())
                .build();
    }
}
