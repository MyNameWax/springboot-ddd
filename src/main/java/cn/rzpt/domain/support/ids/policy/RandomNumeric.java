package cn.rzpt.domain.support.ids.policy;

import cn.rzpt.domain.support.ids.IIdGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;


@Component
public class RandomNumeric implements IIdGenerator {
    @Override
    public long nextId() {
        return Long.parseLong(RandomStringUtils.randomNumeric(11));
    }
}
