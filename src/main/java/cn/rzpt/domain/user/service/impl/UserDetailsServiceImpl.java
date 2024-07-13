package cn.rzpt.domain.user.service.impl;

import cn.hutool.core.util.ObjUtil;
import cn.rzpt.infrastructure.mybatis.mapper.RoleMapper;
import cn.rzpt.infrastructure.mybatis.mapper.UserMapper;
import cn.rzpt.infrastructure.mybatis.mapper.UserRoleMapper;
import cn.rzpt.infrastructure.mybatis.po.RolePO;
import cn.rzpt.infrastructure.mybatis.po.UserPO;
import cn.rzpt.infrastructure.mybatis.po.UserRolePO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final UserRoleMapper userRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPO userPO = userMapper.selectOne(new LambdaQueryWrapper<UserPO>()
                .eq(UserPO::getUsername, username));
        if (ObjUtil.isNull(userPO)) {
            throw new UsernameNotFoundException("账号或密码错误");
        }
        LambdaQueryWrapper<UserRolePO> eq = Wrappers.lambdaQuery(UserRolePO.class)
                .eq(UserRolePO::getUId, userPO.getId());
        List<UserRolePO> userRolePOS = userRoleMapper.selectList(eq);
        List<Long> list = userRolePOS.stream().map(UserRolePO::getRId).toList();
        List<RolePO> rolePOS = roleMapper.selectBatchIds(list);
        userPO.setRole(rolePOS);
        //TODO 通过角色信息查询权限信息(order:create、order:delete)
        return userPO;
    }
}
