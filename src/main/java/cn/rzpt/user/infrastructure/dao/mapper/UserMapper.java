package cn.rzpt.user.infrastructure.dao.mapper;

import cn.rzpt.user.infrastructure.dao.entity.UserPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserPO> {


}
