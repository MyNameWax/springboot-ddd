package cn.rzpt.infrastructure.util;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 日志工具类
 */
public class LogUtil {

    public static final String DEFAULT_LOG_ADDR = "127.0.0.1";

    /**
     * 获取用户IP地址
     *
     * @param request 请求参数
     * @return IP地址
     */
    public static String getUserIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if ("0:0:0:0:0:0:0:1".equals(ip)) {
                ip = DEFAULT_LOG_ADDR;
            }
        }
        if ("unknown".equalsIgnoreCase(ip)) {
            ip = DEFAULT_LOG_ADDR;
            return ip;
        }
        int index = ip.indexOf(',');
        if (index >= 0) {
            ip = ip.substring(0, index);
        }
        return ip;
    }

}
