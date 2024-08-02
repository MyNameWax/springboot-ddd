package cn.rzpt.user.domain.repository;

import cn.rzpt.role.application.res.RoleResp;

public interface IUserRoleRepository {

    /**
     * 根据ID查询用户角色信息
     *
     * @param id 用户ID
     * @return RoleResp
     */
    RoleResp getRoleById(Long id);
}
