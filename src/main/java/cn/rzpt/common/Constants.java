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

    /**
     * 抽奖策略：总体概率，单项概率
     * 场景：两种抽奖算法描述，场景A20%，B30%。C50%
     * 单项概率：如果A被抽完后，B和C保持目前中奖概率，用户抽奖仍有20%中奖为A。因A库存抽空则结果为未中奖，为了运营成本，通常使用这个
     * 总体概率：如果A被抽完后，B和C奖品的概率按3：5均分，相当于B奖品中奖概率有0.3升为0.375
     */
    public enum StrategyMode {
        SINGLE(1, "单项概率"),
        ENTIRETY(2, "总体概率");
        private Integer code;
        private String info;

        StrategyMode(Integer code, String info) {
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

    public enum AwardState {
        WAIT(0, "等待发奖"),
        SUCCESS(1, "发奖成功"),
        FAILURE(2, "发奖失败");

        private Integer code;
        private String info;

        AwardState(Integer code, String info) {
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

    //ids 生成策略枚举
    public enum Ids {
        //雪花算法
        SnowFlake,
        //日期算法
        ShortCode,
        //随机算法
        RandomNumeric;
    }

    //活动单使用状态 0 未使用 1 已使用
    public enum TaskState {
        NO_USED(0, "未使用"),
        USED(1, "已使用");
        private Integer code;
        private String info;

        TaskState() {
        }

        TaskState(Integer code, String info) {
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

    //发奖状态   0 初始，1 完成 2 失败
    public enum GrantState {
        INIT(0, "初始"),
        COMPLETE(1, "完成"),
        FAIL(2, "失败");
        private Integer code;
        private String info;

        GrantState() {
        }

        GrantState(Integer code, String info) {
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
