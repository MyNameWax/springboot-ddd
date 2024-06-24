package cn.rzpt.infrastructure.mapper;

import cn.rzpt.domain.user.model.req.UserLoginReq;
import cn.rzpt.infrastructure.po.UserPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserPO> {


}
