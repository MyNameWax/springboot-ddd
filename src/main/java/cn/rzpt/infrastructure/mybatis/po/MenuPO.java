package cn.rzpt.infrastructure.mybatis.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@TableName("menu")
@NoArgsConstructor
@AllArgsConstructor
public class MenuPO {

    /**
     * ID编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 父ID
     */
    private Long parentId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 菜单类型
     */
    private Integer menuType;

    /**
     * 菜单路径
     */
    private String path;

    /**
     * 组件路径
     */
    private String componentPath;

    /**
     * 权限
     */
    private String perms;

    /**
     * 图标
     */
    private String icon;

}
