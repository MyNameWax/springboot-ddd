package cn.rzpt.user.application.event;

import cn.rzpt.role.domain.repository.IRoleRepository;
import cn.rzpt.framework.base.event.BaseDomainEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户角色列表事件
 */
@Slf4j
public class UserRoleListEvent extends BaseDomainEvent {


    private final IRoleRepository roleRepository;

    public UserRoleListEvent(Long id, IRoleRepository roleRepository) {
        super(id, "cn.rzpt.domain.user.service.impl.UserServiceImpl[method - register]");
        this.roleRepository = roleRepository;
        roleRepository.addDefaultRole(id);

    }

}
