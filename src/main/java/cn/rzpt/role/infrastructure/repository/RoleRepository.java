package cn.rzpt.role.infrastructure.repository;

import cn.hutool.core.util.StrUtil;
import cn.rzpt.framework.web.result.Constants;
import cn.rzpt.role.application.req.RolePageReq;
import cn.rzpt.role.application.res.RoleResp;
import cn.rzpt.role.domain.repository.IRoleRepository;
import cn.rzpt.user.infrastructure.dao.mapper.RoleMapper;
import cn.rzpt.user.infrastructure.dao.mapper.UserRoleMapper;
import cn.rzpt.user.infrastructure.dao.entity.RolePO;
import cn.rzpt.user.infrastructure.dao.entity.UserRolePO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 角色存储表
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RoleRepository implements IRoleRepository {

    private final UserRoleMapper userRoleMapper;
    private final RoleMapper roleMapper;


    /**
     * 添加默认角色
     *
     * @param userId 用户ID
     */
    @Override
    public void addDefaultRole(Long userId) {

        log.info("用户:{},添加默认角色", userId);
        UserRolePO userRolePO = UserRolePO.builder()
                .uId(userId)
                .rId(Constants.Role.USER.getId())
                .build();
        userRoleMapper.insert(userRolePO);
    }


    /**
     * 角色列表
     *
     * @param req 分页请求
     * @return 角色列表
     */
    @Override
    public Page<RoleResp> roleList(RolePageReq req) {
        Page<RolePO> page = new Page<>(req.getPageNum(), req.getPageSize());
        LambdaQueryWrapper<RolePO> searchKeyWordWrapper = Wrappers.lambdaQuery(RolePO.class)
                .like(StrUtil.isNotEmpty(req.getKeyword()), RolePO::getName, req.getKeyword());
        Page<RolePO> rolePOPage = roleMapper.selectPage(page, searchKeyWordWrapper);
        Page<RoleResp> roleVOPage = new Page<>();
        roleVOPage.setSize(rolePOPage.getSize());
        roleVOPage.setCurrent(rolePOPage.getCurrent());
        roleVOPage.setTotal(rolePOPage.getTotal());
        roleVOPage.setRecords(rolePOPage.getRecords().stream().map(rolePO -> RoleResp.builder()
                .code(rolePO.getCode())
                .desc(rolePO.getDesc())
                .name(rolePO.getName())
                .build()).toList());
        return roleVOPage;
    }

}
