package cn.rzpt.domain.user.repository;

import cn.rzpt.domain.role.model.vo.RoleVO;

public interface IUserRoleRepository {

    /**
     * 根据ID查询用户角色信息
     *
     * @param id 用户ID
     * @return RoleVO
     */
    RoleVO getRoleById(Long id);
}
