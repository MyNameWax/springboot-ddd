package cn.rzpt.domain.user.model.convert.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.rzpt.domain.user.model.convert.UserInfoVOMapper;
import cn.rzpt.domain.user.model.vo.UserInfoVO;
import cn.rzpt.infrastructure.po.UserPO;

public class UserInfoVOMapperImpl implements UserInfoVOMapper {


    @Override
    public UserInfoVO toVo(UserPO userPO) {
        if (ObjectUtil.isNull(userPO)) {
            return null;
        }
        return UserInfoVO.builder()
                .id(userPO.getId())
                .username(userPO.getUsername())
                .email(userPO.getEmail())
                .address(userPO.getAddress())
                .avatar(userPO.getAvatar())
                .build();
    }
}
