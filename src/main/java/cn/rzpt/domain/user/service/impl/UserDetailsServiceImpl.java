package cn.rzpt.domain.user.service.impl;

import cn.hutool.core.util.ObjUtil;
import cn.rzpt.infrastructure.mapper.UserMapper;
import cn.rzpt.infrastructure.po.UserPO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPO userPO = userMapper.selectOne(new LambdaQueryWrapper<UserPO>()
                .eq(UserPO::getUsername, username));
        if (ObjUtil.isNull(userPO)) {
            throw new UsernameNotFoundException("账号或密码错误");
        }
        return userPO;
    }
}
