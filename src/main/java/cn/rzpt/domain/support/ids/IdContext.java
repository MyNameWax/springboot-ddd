package cn.rzpt.domain.support.ids;

import cn.rzpt.common.Constants;
import cn.rzpt.domain.support.ids.policy.RandomNumeric;
import cn.rzpt.domain.support.ids.policy.ShortCode;
import cn.rzpt.domain.support.ids.policy.SnowFlake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * ID生成策略类
 */
@Configuration
public class IdContext {

    @Bean
    public Map<Constants.Ids,IIdGenerator> idGenerator(SnowFlake snowFlake, ShortCode shortCode, RandomNumeric randomNumeric) {
        HashMap<Constants.Ids,IIdGenerator> idGeneratorMap = new HashMap<>(8);
        idGeneratorMap.put(Constants.Ids.SnowFlake,snowFlake);
        idGeneratorMap.put(Constants.Ids.ShortCode,shortCode);
        idGeneratorMap.put(Constants.Ids.RandomNumeric,randomNumeric);
        return idGeneratorMap;
    }

}
