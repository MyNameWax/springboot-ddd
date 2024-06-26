package cn.rzpt.infrastructure.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@TableName("role")
@NoArgsConstructor
@AllArgsConstructor
public class RolePO {
    private Long id;
    private String code;
    private String name;
    private String desc;
}
