package cn.rzpt.user.infrastructure.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@TableName("user_role")
@NoArgsConstructor
@AllArgsConstructor
public class UserRolePO {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long uId;
    private Long rId;
}
