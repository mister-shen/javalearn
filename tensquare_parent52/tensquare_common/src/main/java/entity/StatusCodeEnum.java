package entity;

/**
 * @author shenrs
 * @Description 返回状态枚举
 * @create 2019-07-23 13:27
 **/
public enum StatusCodeEnum {
    STATUS_OK(20000, "成功"),
    STATUS_SEARCH_OK(20000, "查询成功"),
    STATUS_SAVE_OK(20000, "添加成功"),
    STATUS_UPDATE_OK(20000, "更新成功"),
    STATUS_DEL_OK(20000, "删除成功"),

    STATUS_ERROR(20001, "失败"),
    STATUS_LOGIN_ERROR(20002, "用户名或密码错误"),
    STATUS_ACCESS_ERROR(20003, "权限不足"),
    STATUS_REMOTE_ERROR(20004, "远程调用失败"),
    STATUS_REPE_ERROR(20005, "重复操作");

    private Integer code;

    private String desc;

    StatusCodeEnum() {
    }

    StatusCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
