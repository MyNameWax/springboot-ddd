package cn.rzpt.user.application.service.impl;

import cn.hutool.core.util.ObjUtil;
import cn.rzpt.user.infrastructure.dao.entity.*;
import cn.rzpt.user.infrastructure.dao.mapper.*;
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
    private final MenuMapper menuMapper;
    private final RoleMenuMapper roleMenuMapper;
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
        List<Long> roleIds = rolePOS.stream().map(RolePO::getId).toList();
        LambdaQueryWrapper<RoleMenuPO> roleMenuQueryWrapper = Wrappers.lambdaQuery(RoleMenuPO.class)
                .in(RoleMenuPO::getRoleId, roleIds);
        List<RoleMenuPO> roleMenuPOS = roleMenuMapper.selectList(roleMenuQueryWrapper);
        List<Long> menuIdList = roleMenuPOS.stream().map(RoleMenuPO::getMenuId).toList();
        List<MenuPO> menuList = menuMapper.selectBatchIds(menuIdList);
        List<String> userPerms = menuList.stream().map(MenuPO::getPerms).toList();
        userPO.setPerms(userPerms);
        return userPO;
    }
}
