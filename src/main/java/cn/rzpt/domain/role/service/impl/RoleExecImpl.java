package cn.rzpt.domain.role.service.impl;

import cn.rzpt.domain.role.model.req.RolePageReq;
import cn.rzpt.domain.role.model.vo.RoleVO;
import cn.rzpt.domain.role.service.IRoleExec;
import cn.rzpt.infrastructure.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleExec")
@RequiredArgsConstructor
public class RoleExecImpl implements IRoleExec {

    private final RoleRepository roleRepository;

    @Override
    public List<RoleVO> roleList(RolePageReq req) {
        return roleRepository.roleList(req);
    }
}
