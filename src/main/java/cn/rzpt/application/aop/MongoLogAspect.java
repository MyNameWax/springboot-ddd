package cn.rzpt.application.aop;

import cn.rzpt.domain.log.service.LogsService;
import cn.rzpt.domain.user.model.aggregates.UserRoleAggregates;
import cn.rzpt.infrastructure.mongo.po.LogPO;
import cn.rzpt.infrastructure.util.LogUtil;
import com.alibaba.fastjson.JSON;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;

@Aspect
@Slf4j
@Component
@RequiredArgsConstructor
public class MongoLogAspect {

    private final LogsService logsService;

    @Pointcut("execution(* cn.rzpt.adapter.user.*.*(..))")
    public void logPointCut() {
    }

    @AfterReturning(value = "logPointCut()", returning = "value")
    public void afterLog(JoinPoint joinPoint, Object value) {
        log.info("日志切点进入成功");
        //获取请求参数
        String requestParam = "";
        LogPO logPO = new LogPO();
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            logPO.setUserId(0L);
            logPO.setUsername("匿名用户");
        } else {
            UserRoleAggregates userRoleAggregates = (UserRoleAggregates) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logPO.setUserId(userRoleAggregates.getUserInfoVO().getId());
            logPO.setUsername(userRoleAggregates.getUserInfoVO().getUsername());
        }
        logPO.setClassName(joinPoint.getTarget().getClass().getSimpleName());
        logPO.setMethod(joinPoint.getSignature().getName());
        logPO.setCreateDate(LocalDate.now());
        logPO.setResponseResults(requestParam);
        if (value != null) {
            logPO.setResponseResults(JSON.toJSONString(value));
        }
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        logPO.setRequestURI(request.getRequestURI());
        logPO.setIp(LogUtil.getUserIP(request));
        logsService.save(logPO);
        log.info("日志切点结束");

    }


}
