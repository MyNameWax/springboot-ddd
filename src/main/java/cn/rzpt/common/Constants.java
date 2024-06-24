package cn.rzpt.common;

public class Constants {
    public enum ResponseCode {
        SUCCESS("0000", "成功"),
        UN_ERROR("0001","未知失败"),
        ILLEGAL_PARAMETER("0002","非法参数"),
        INDEX_DUP("0003","主键冲突"),
        NO_UPDATE("0004","SQL操作无更新"),
        LOSING_DRAW("D001","未中奖"),
        RULE_ERR("DOO2","量化人群规则执行失败"),
        NOT_CONSUMED_TAKE("D003","未消费活动领取记录"),
        OUT_OF_STOCK("D004","活动无库存"),
        ERR_TOKEN("D005","分布式锁失败");
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
    public static final class Global{
        public static final Long TREE_NULL_NODE = 0L;
    }
    /**
     * 缓存key
     * */
    public static final class RedisKey{
        //抽奖活动库存key
        private static final String LOTTERY_ACTIVITY_STOCK_COUNT = "lottery_activity_stock_count_";
        public static String KEY_LOTTERY_ACTIVITY_STOCK_COUNT(Long activityId){
            return LOTTERY_ACTIVITY_STOCK_COUNT + activityId;
        }
        //抽奖活动库存锁
        private static final String LOTTERY_ACTIVITY_STOCK_COUNT_TOKEN="lottery_activity_stock_count_token_";
        public static String KEY_LOTTERY_ACTIVITY_STOCK_COUNT_TOKEN(Long activityId,Integer stockUsedCount){
            return LOTTERY_ACTIVITY_STOCK_COUNT_TOKEN + activityId + "_" + stockUsedCount;
        }
    }
    /**
     * 决策树节点
     * */
    public static final class NodeType{
        public static final Integer STEM=1;
        public static final Integer FRUIT=2;
    }
    /**
     * 规则限定类型
     * */
    public static final class RuleLimitType{
        public static final int EQUAL=1;//等于
        public static final int GT = 2;//大于
        public static final int LT = 3;//小于
        public static final int GE = 4;//大于等于
        public static final int LE = 5;//小于等于
        public static final int ENUM = 6;//枚举
    }
    /**
     *
     *
     * */
    public enum ActivityState{
        EDIT(1,"编辑"),
        ARRAIGNMENT(2,"提审"),
        REVOKE(3,"撤审"),
        PASS(4,"通过"),
        DOING(5,"运行（活动中）"),
        REFUSE(6,"拒绝"),
        CLOSE(7,"关闭"),
        OPEN(8,"开启");

        private Integer code;
        private String Info;

        ActivityState() {
        }

        ActivityState(Integer code, String Info) {
            this.code = code;
            this.Info = Info;
        }

        /**
         * 获取
         * @return code
         */
        public Integer getCode() {
            return code;
        }

        /**
         * 设置
         * @param code
         */
        public void setCode(Integer code) {
            this.code = code;
        }

        /**
         * 获取
         * @return Info
         */
        public String getInfo() {
            return Info;
        }

        /**
         * 设置
         * @param Info
         */
        public void setInfo(String Info) {
            this.Info = Info;
        }
    }
    /**
     * 抽奖策略：总体概率，单项概率
     * 场景：两种抽奖算法描述，场景A20%，B30%。C50%
     * 单项概率：如果A被抽完后，B和C保持目前中奖概率，用户抽奖仍有20%中奖为A。因A库存抽空则结果为未中奖，为了运营成本，通常使用这个
     * 总体概率：如果A被抽完后，B和C奖品的概率按3：5均分，相当于B奖品中奖概率有0.3升为0.375
     * */
    public enum StrategyMode{
        SINGLE(1,"单项概率"),
        ENTIRETY(2,"总体概率");
        private Integer code;
        private String info;

       StrategyMode(Integer code,String info){
           this.code=code;
           this.info=info;
       }

        /**
         * 获取
         * @return code
         */
        public Integer getCode() {
            return code;
        }

        /**
         * 设置
         * @param code
         */
        public void setCode(Integer code) {
            this.code = code;
        }

        /**
         * 获取
         * @return info
         */
        public String getInfo() {
            return info;
        }

        /**
         * 设置
         * @param info
         */
        public void setInfo(String info) {
            this.info = info;
        }
    }
    /**
     * 中奖状态 0未中奖，1已中奖 2 兜底奖
     * */
    public enum DrawState{
        FAIL(0,"未中奖"),
        SUCCESS(1,"已中奖"),
        Cover(2,"兜底奖");
        private Integer code;
        private String info;


        DrawState(Integer code,String info) {
            this.code = code;
            this.info = info;
        }

        /**
         * 获取
         * @return code
         */
        public Integer getCode() {
            return code;
        }

        /**
         * 设置
         * @param code
         */
        public void setCode(Integer code) {
            this.code = code;
        }

        /**
         * 获取
         * @return info
         */
        public String getInfo() {
            return info;
        }

        /**
         * 设置
         * @param info
         */
        public void setInfo(String info) {
            this.info = info;
        }
    }
    public enum AwardState{

        WAIT(0,"等待发奖"),

        SUCCESS(1,"发奖成功"),
        FAILURE(2,"发奖失败");

        private Integer code;
        private String info;

        AwardState( Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        /**
         * 获取
         * @return code
         */
        public Integer getCode() {
            return code;
        }

        /**
         * 设置
         * @param code
         */
        public void setCode(Integer code) {
            this.code = code;
        }

        /**
         * 获取
         * @return info
         */
        public String getInfo() {
            return info;
        }

        /**
         * 设置
         * @param info
         */
        public void setInfo(String info) {
            this.info = info;
        }
    }
    public enum AwardType{

        DESC(1,"文字描述"),
        RedeemCodeGoods(2,"兑换码"),
        CouponGoods(3,"优惠券"),
        PhysicalGoods(4,"实物奖品");

        private Integer code;
        private String info;


        AwardType() {
        }

        AwardType(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        /**
         * 获取
         * @return code
         */
        public Integer getCode() {
            return code;
        }

        /**
         * 设置
         * @param code
         */
        public void setCode(Integer code) {
            this.code = code;
        }

        /**
         * 获取
         * @return info
         */
        public String getInfo() {
            return info;
        }

        /**
         * 设置
         * @param info
         */
        public void setInfo(String info) {
            this.info = info;
        }
    }
    //ids 生成策略枚举
    public enum Ids{
        //雪花算法
        SnowFlake,
        //日期算法
        ShortCode,
        //随机算法
        RandomNumeric;
    }
    //活动单使用状态 0 未使用 1 已使用
    public enum TaskState{
        NO_USED(0,"未使用"),
        USED(1,"已使用");
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
         * @return code
         */
        public Integer getCode() {
            return code;
        }

        /**
         * 设置
         * @param code
         */
        public void setCode(Integer code) {
            this.code = code;
        }

        /**
         * 获取
         * @return info
         */
        public String getInfo() {
            return info;
        }

        /**
         * 设置
         * @param info
         */
        public void setInfo(String info) {
            this.info = info;
        }
    }
    //发奖状态   0 初始，1 完成 2 失败
    public enum GrantState{
        INIT(0,"初始"),
        COMPLETE(1,"完成"),
        FAIL(2,"失败");
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
         * @return code
         */
        public Integer getCode() {
            return code;
        }

        /**
         * 设置
         * @param code
         */
        public void setCode(Integer code) {
            this.code = code;
        }

        /**
         * 获取
         * @return info
         */
        public String getInfo() {
            return info;
        }

        /**
         * 设置
         * @param info
         */
        public void setInfo(String info) {
            this.info = info;
        }
    }
    //消息发送状态
    public enum MQState{
        INIT(0,"初始"),
        COMPLETE(1,"完成"),
        FAIL(2,"失败");
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
         * @return code
         */
        public Integer getCode() {
            return code;
        }

        /**
         * 设置
         * @param code
         */
        public void setCode(Integer code) {
            this.code = code;
        }

        /**
         * 获取
         * @return info
         */
        public String getInfo() {
            return info;
        }

        /**
         * 设置
         * @param info
         */
        public void setInfo(String info) {
            this.info = info;
        }
    }
}
