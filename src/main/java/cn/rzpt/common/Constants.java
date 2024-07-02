package cn.rzpt.common;

public class Constants {
    public enum ResponseCode {
        SUCCESS("0000", "成功"),
        UN_ERROR("0001", "未知失败"),
        ILLEGAL_PARAMETER("0002", "非法参数"),
        INDEX_DUP("0003", "主键冲突"),
        NO_UPDATE("0004", "SQL操作无更新");
        private String code;
        private String info;

        ResponseCode(String code, String info) {
            this.code = code;
            this.info = info;
        }

        public String getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }

    }

    public static final class Global {
        public static final Long TREE_NULL_NODE = 0L;
    }

    /**
     * 缓存key
     */
    public static final class RedisKey {
        //登录用户信息
        public static final String LOGIN_USER_INFO = "login:user:info:";

    }

    //ids 生成策略枚举
    public enum Ids {
        //雪花算法
        SnowFlake,
        //日期算法
        ShortCode,
        //随机算法
        RandomNumeric;
    }

    // 角色信息枚举
    public enum Role {
        // 普通用户
        USER(1L, "USER"),
        // 管理员
        ADMIN(2L, "ADMIN");
        private Long id;
        private String name;

        Role(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    //消息发送状态
    public enum MQState {
        INIT(0, "初始"),
        COMPLETE(1, "完成"),
        FAIL(2, "失败");
        private Integer code;
        private String info;

        MQState() {
        }

        MQState(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        /**
         * 获取
         *
         * @return code
         */
        public Integer getCode() {
            return code;
        }

        /**
         * 设置
         *
         * @param code
         */
        public void setCode(Integer code) {
            this.code = code;
        }

        /**
         * 获取
         *
         * @return info
         */
        public String getInfo() {
            return info;
        }

        /**
         * 设置
         *
         * @param info
         */
        public void setInfo(String info) {
            this.info = info;
        }
    }
}
