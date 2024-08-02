package cn.rzpt.role.domain.repository;

import cn.rzpt.role.application.req.RolePageReq;
import cn.rzpt.role.application.res.RoleResp;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface IRoleRepository {

    /**
     * 添加用户默认角色
     *
     * @param userId 用户ID
     */
    void addDefaultRole(Long userId);


    /**
     * 角色列表
     *
     * @param req 请求对象封装体
     * @return List<RoleResp>
     */
    Page<RoleResp> roleList(RolePageReq req);

}
