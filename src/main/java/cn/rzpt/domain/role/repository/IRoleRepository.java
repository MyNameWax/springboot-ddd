package cn.rzpt.domain.role.repository;

import cn.rzpt.domain.role.model.req.RolePageReq;
import cn.rzpt.domain.role.model.vo.RoleVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

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
     * @return List<RoleVO>
     */
    Page<RoleVO> roleList(RolePageReq req);

}
