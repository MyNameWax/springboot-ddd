package cn.rzpt.domain.user.service.event;

import cn.rzpt.domain.role.repository.IRoleRepository;
import cn.rzpt.domain.support.model.event.BaseDomainEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

/**
 * 用户角色列表事件
 */
@Slf4j
public class UserRoleListEvent extends BaseDomainEvent {


    private final IRoleRepository roleRepository;

    public UserRoleListEvent(Long id, IRoleRepository roleRepository) {
        super(id, "cn.rzpt.domain.user.service.impl.UserExecImpl[method - register]");
        this.roleRepository = roleRepository;
        roleRepository.addDefaultRole(id);
    }

}
