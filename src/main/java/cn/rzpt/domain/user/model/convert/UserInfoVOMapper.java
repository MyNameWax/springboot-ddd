package cn.rzpt.domain.user.model.convert;

import cn.rzpt.domain.user.model.vo.UserInfoVO;
import cn.rzpt.infrastructure.mybatis.po.UserPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UserInfoVOMapper {
    UserInfoVOMapper INSTANCE = Mappers.getMapper( UserInfoVOMapper.class );
    UserInfoVO toVo(UserPO userPO);
}
