package cn.rzpt.adapter.role;

import cn.rzpt.common.Constants;
import cn.rzpt.common.Result;
import cn.rzpt.domain.role.model.req.RolePageReq;
import cn.rzpt.domain.role.repository.IRoleRepository;
import cn.rzpt.domain.role.service.IRoleExec;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class InterRoleAdapter {

    private final IRoleExec roleExec;
    private final IRoleRepository roleRepository;


    /**
     * 获取角色列表
     *
     * @param req 分页对象
     * @return 角色列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasPermission('/role/list','role:list')")
    public Result getRoleList(RolePageReq req) {
        return Result.buildResult(Constants.ResponseCode.SUCCESS, roleRepository.roleList(req));
    }

}
