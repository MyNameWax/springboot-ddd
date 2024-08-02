package cn.rzpt.role.interfaces;

import cn.rzpt.framework.web.result.Constants;
import cn.rzpt.framework.web.result.Result;
import cn.rzpt.role.application.req.RolePageReq;
import cn.rzpt.role.domain.repository.IRoleRepository;
import cn.rzpt.role.application.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private final IRoleService roleService;
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
