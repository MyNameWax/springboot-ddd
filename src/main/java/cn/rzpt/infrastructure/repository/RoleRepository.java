package cn.rzpt.infrastructure.repository;

import cn.hutool.core.lang.Assert;
import cn.rzpt.domain.role.model.req.RolePageReq;
import cn.rzpt.domain.role.model.vo.RoleVO;
import cn.rzpt.domain.role.repository.IRoleRepository;
import cn.rzpt.infrastructure.mapper.RoleMapper;
import cn.rzpt.infrastructure.mapper.UserMapper;
import cn.rzpt.infrastructure.po.RolePO;
import cn.rzpt.infrastructure.po.UserPO;
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

    private final RoleMapper roleMapper;
    private final UserMapper userMapper;


    /**
     * 添加默认角色
     *
     * @param userId 用户ID
     */
    @Override
    public void addDefaultRole(Long userId) {
        UserPO userPO = checkAddDefaultRoleParams(userId);
        //TODO 给用户添加默认角色
        log.info("给用户添加默认角色");
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

    /**
     * 校验添加默认角色参数
     *
     * @param userId 用户id
     */
    private UserPO checkAddDefaultRoleParams(Long userId) {
        UserPO userPO = userMapper.selectById(userId);
        Assert.isNull(userPO, "用户不存在");
        return userPO;
    }
}
