package com.shenrs.entity;

/**
 * @author shenrs
 * @Description 用户实体类
 * @create 2018-09-03 16:06
 **/
public class UserEntity {
    private String userId;

    private String userName;

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
