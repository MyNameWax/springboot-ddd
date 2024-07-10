package cn.rzpt.domain.log.service.impl;

import cn.rzpt.domain.log.service.LogsService;
import cn.rzpt.infrastructure.mongo.po.LogPO;
import com.anwen.mongo.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class LogsServiceImpl extends ServiceImpl<LogPO> implements LogsService {
}
