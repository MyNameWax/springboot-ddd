package cn.rzpt.infrastructure.mybatis.mapper;

import cn.rzpt.infrastructure.mybatis.po.MenuPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenuMapper extends BaseMapper<MenuPO> {
}
