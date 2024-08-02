package cn.rzpt.framework.base.event;

import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * 领域事件发布实现类
 */
@Slf4j
@Component
public class DomainEventPublisherImpl implements DomainEventPublisher {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publishEvent(BaseDomainEvent event) {
        log.info("发布事件,:{}", JSON.toJSONString(event));
        applicationEventPublisher.publishEvent(event);
    }
}
