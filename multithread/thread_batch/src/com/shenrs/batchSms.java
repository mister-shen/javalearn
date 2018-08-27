package com.shenrs;

import com.shenrs.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

class UserSendThread implements Runnable{
    private List<UserEntity> listUser;

    public UserSendThread(List<UserEntity> listUser) {
        this.listUser = listUser;
    }

    @Override
    public void run() {
        for (UserEntity user : listUser) {
            System.out.println(Thread.currentThread().getName() + ", " + user.toString());
        }
        System.out.println();
    }
}


/**
 * @author shenrs
 * @Description 模拟批量发送邮件
 * @create 2018-08-27 11:24
 **/
public class batchSms {
    public static void main(String[] args) {
        // 1.初始化数据
        ArrayList<UserEntity> userList = initData();
        // 2.定义每个线程分批发送的大小
        int pageSize = 2;
        // 3.计算每个线程需要分批跑的数据
        List<List<UserEntity>> splitList = ListUtils.splitList(userList, pageSize);
        int i = 0;
        // 4.分配发送
        for (List<UserEntity> users : splitList) {
            Thread thread = new Thread(new UserSendThread(users),"线程" + i);
            i++;
            thread.start();
        }

    }

    private static ArrayList<UserEntity> initData() {
        ArrayList<UserEntity> userList = new ArrayList<>();
        for(int i = 0; i <= 10; i++){
            userList.add(new UserEntity("userId" + i, "username" + i));
        }
        return userList;
    }
}
