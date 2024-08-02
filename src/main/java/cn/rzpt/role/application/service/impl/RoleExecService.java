package cn.rzpt.role.application.service.impl;

import cn.rzpt.role.application.service.IRoleService;
import cn.rzpt.role.infrastructure.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleExecService implements IRoleService {

    private final RoleRepository roleRepository;



}
