package cn.rzpt.domain.user.model.convert;

import cn.rzpt.domain.user.model.vo.UserInfoVO;
import cn.rzpt.infrastructure.po.UserPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserInfoVOMapper {
    UserInfoVOMapper INSTANCE = Mappers.getMapper( UserInfoVOMapper.class );
    UserInfoVO toVo(UserPO userPO);
}
