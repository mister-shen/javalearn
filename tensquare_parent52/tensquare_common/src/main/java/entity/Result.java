package entity;

import com.sun.net.httpserver.Authenticator;

/**
 * @author shenrs
 * @Description 统一返回对象类
 * @create 2019-07-23 13:22
 **/
public class Result {

    private boolean flag;
    private Integer code;
    private String message;
    private Object data;


    public Result() {
    }

    public static Result success(){
        return Result.success(StatusCodeEnum.STATUS_OK, null);
    }

    public static Result success(StatusCodeEnum codeEnum){
        return Result.success(codeEnum, null);
    }

    public static Result success(String message){
        StatusCodeEnum codeEnum = StatusCodeEnum.STATUS_OK;
        codeEnum.setDesc(message);
        return Result.success(codeEnum, null);
    }

    public static Result success(Integer code, String message){
        StatusCodeEnum codeEnum = StatusCodeEnum.STATUS_OK;
        codeEnum.setCode(code);
        codeEnum.setDesc(message);
        return Result.success(codeEnum, null);
    }

    public static Result success(String message, Object data){
        StatusCodeEnum codeEnum = StatusCodeEnum.STATUS_OK;
        codeEnum.setDesc(message);
        return Result.success(codeEnum, data);
    }

    public static Result success(Integer code, String message, Object data){
        StatusCodeEnum codeEnum = StatusCodeEnum.STATUS_OK;
        codeEnum.setCode(code);
        codeEnum.setDesc(message);
        return Result.success(codeEnum, data);
    }

    public static Result success(StatusCodeEnum codeEnum, Object data){
        Result result = new Result();
        result.setFlag(true);
        result.setCode(codeEnum.getCode());
        result.setMessage(codeEnum.getDesc());
        result.setData(data);
        return result;
    }

    public static Result fail() {
        StatusCodeEnum codeEnum = StatusCodeEnum.STATUS_ERROR;
        return fail(codeEnum);
    }

    public static Result fail(String message) {
        StatusCodeEnum codeEnum = StatusCodeEnum.STATUS_ERROR;
        codeEnum.setDesc(message);
        return fail(codeEnum);
    }

    public static Result fail(Integer code, String message) {
        StatusCodeEnum codeEnum = StatusCodeEnum.STATUS_ERROR;
        codeEnum.setCode(code);
        codeEnum.setDesc(message);
        return fail(codeEnum);
    }

    public static Result fail(StatusCodeEnum codeEnum) {
        Result result = new Result();
        result.setFlag(false);
        result.setCode(codeEnum.getCode());
        result.setMessage(codeEnum.getDesc());
        return result;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
