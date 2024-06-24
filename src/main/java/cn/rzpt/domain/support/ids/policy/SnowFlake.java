package cn.rzpt.domain.support.ids.policy;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import cn.rzpt.domain.support.ids.IIdGenerator;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;


/**
 * @description
 * @author:吴海龙 date:2023/6/15
 * Copyright:wuhailong
 */
@Component
public class SnowFlake implements IIdGenerator {
    private Snowflake snowFlake;
    @PostConstruct
    public void init(){
        long workerId;
        try{
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        }catch (Exception e){
            workerId = NetUtil.getLocalhostStr().hashCode();
        }
        workerId = workerId>>16 & 31;
        long dataCenterId = 1L;
        snowFlake = IdUtil.createSnowflake(workerId,dataCenterId);
    }
    @Override
    public synchronized long nextId() {
        return snowFlake.nextId();
    }
}
