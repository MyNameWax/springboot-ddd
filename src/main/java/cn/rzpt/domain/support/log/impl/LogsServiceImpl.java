package cn.rzpt.domain.support.log.impl;

import cn.rzpt.domain.support.log.LogsService;
import cn.rzpt.infrastructure.mongo.po.LogPO;
import com.anwen.mongo.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class LogsServiceImpl extends ServiceImpl<LogPO> implements LogsService {
}
