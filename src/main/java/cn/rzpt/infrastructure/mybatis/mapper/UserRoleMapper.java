package cn.rzpt.infrastructure.mybatis.mapper;

import cn.rzpt.infrastructure.mybatis.po.UserRolePO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRolePO> {
}
