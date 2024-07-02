package cn.rzpt.infrastructure.repository;

import cn.hutool.core.lang.Assert;
import cn.rzpt.common.Constants;
import cn.rzpt.domain.role.model.req.RolePageReq;
import cn.rzpt.domain.role.model.vo.RoleVO;
import cn.rzpt.domain.role.repository.IRoleRepository;
import cn.rzpt.infrastructure.mapper.RoleMapper;
import cn.rzpt.infrastructure.mapper.UserMapper;
import cn.rzpt.infrastructure.mapper.UserRoleMapper;
import cn.rzpt.infrastructure.po.RolePO;
import cn.rzpt.infrastructure.po.UserPO;
import cn.rzpt.infrastructure.po.UserRolePO;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

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
        log.info("用户:{},添加默认角色",userId);
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
    public List<RoleVO> roleList(RolePageReq req) {
        Page<RolePO> page = new Page<>(req.getPageNum(), req.getPageSize());
        Page<RolePO> rolePOPage = roleMapper.selectPage(page, Wrappers.emptyWrapper());
        return rolePOPage.getRecords().stream().map(item -> RoleVO.builder()
                .code(item.getCode())
                .name(item.getName())
                .desc(item.getDesc())
                .build()).toList();
    }

}
