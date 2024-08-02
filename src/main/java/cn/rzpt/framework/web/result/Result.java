package cn.rzpt.framework.web.result;

import java.io.Serializable;

public class Result implements Serializable {
    private static final long serialVersionUID = -3826891916021780628L;
    private String code;
    private Object info;

    public static Result buildResult(Constants.ResponseCode code) {
        return new Result(code.getCode(),code.getInfo());
    }
    public static Result buildResult(Constants.ResponseCode code, String info) {
        return new Result(code.getCode(), info);
    }
    public static Result buildResult(Constants.ResponseCode code, Object data) {
        return new Result(code.getCode(), data);
    }
    public static Result buildResult(Constants.ResponseCode code, Constants.ResponseCode info) {
        return new Result(code.getCode(), info.getInfo());
    }

    public static Result buildSuccessResult() {
        return new Result(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
    }

    public static Result buildErrorResult() {
        return new Result(Constants.ResponseCode.UN_ERROR.getCode(), Constants.ResponseCode.UN_ERROR.getInfo());
    }
    public static Result buildErrorResult(String info) {
        return new Result(Constants.ResponseCode.UN_ERROR.getCode(), info);
    }

    public Result(String code, Object info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }
}
