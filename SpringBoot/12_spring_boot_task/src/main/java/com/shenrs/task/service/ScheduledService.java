package com.shenrs.task.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author shenrs
 * @Description 定时执行
 * @create 2019-04-25 16:30
 **/
@Service
public class ScheduledService {

    /**
     * second(秒)， minute(分)，hour(时),day(日)，month(月)，week(周)
     * 0 * * * * MON-FRI
     * 【0 0/5 14,18 * * ?】每天14时整和18时整，每隔5分钟执行一次
     * 【0 15 10 ? * 1-6】每个月的周一至周六10:15分执行一次
     * 【0 0 2 ? * 6L】每个月最后一个周六凌晨2点执行一次
     * 【0 0 2 LW * ?】每个月最后一个工作日凌晨2点执行一次
     * 【0 0 2-4 ? * 1#1】每个月的第一个周一凌晨2点到4点，每个整点执行一次
     */
//    @Scheduled(cron = "0 * * * * MON-FRI")/*周一至周五 每分的0秒执行*/
//    @Scheduled(cron = "0,1,2,3,50 * * * * MON-FRI")/*周一至周五 每分的0,1,2,3,50秒执行*/
//    @Scheduled(cron = "0-10 * * * * MON-FRI")/*周一至周五 每分的0~10秒内每秒执行*/
    @Scheduled(cron = "0/4 * * * * MON-FRI")/*周一至周五 每4秒执行一次*/
    public void hello(){
        System.out.println("Scheduled..hello..");
    }
}
