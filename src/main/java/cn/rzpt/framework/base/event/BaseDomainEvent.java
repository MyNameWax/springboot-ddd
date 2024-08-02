package cn.rzpt.framework.base.event;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 通用领域事件通知类
 */
@Data
@NoArgsConstructor
public class BaseDomainEvent<T> implements Serializable {

    @Serial
    private static final Long serialVersionUID = 1L;

    /**
     * 发布时间
     */
    private LocalDateTime publishTime;

    /**
     * 事件来源
     */
    private String source;


    /**
     * 领域事件数据
     */
    private T data;

    public BaseDomainEvent(T data,String source) {
        this.data = data;
        this.source = source;
        this.publishTime = LocalDateTime.now();
    }
}
