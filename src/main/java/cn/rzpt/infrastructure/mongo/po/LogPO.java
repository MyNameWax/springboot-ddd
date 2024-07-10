package cn.rzpt.infrastructure.mongo.po;

import com.anwen.mongo.annotation.ID;
import com.anwen.mongo.annotation.collection.CollectionField;
import com.anwen.mongo.annotation.collection.CollectionName;
import com.anwen.mongo.enums.IdTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogPO {

    /**
     * 主键ID
     */
    @ID(type = IdTypeEnum.ASSIGN_ID)
    private String id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;

    /**
     * 操作IP
     */
    private String ip;

    /**
     * 方法名称
     */
    private String method;

    /**
     * 操作类名
     */
    private String className;

    /**
     * 请求地址
     */
    private String requestURI;

    /**
     * 响应结果
     */
    private String responseResults;

}
