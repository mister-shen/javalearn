package com.shenrs.rules;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shenrs
 * @Description 每个服务轮询五次的规则
 * @create 2019-05-10 10:37
 **/
public class RoundRobinFiveRule extends AbstractLoadBalancerRule {

    private AtomicInteger nextServerCyclicCounter; //下一个服务器循环计数器
    private AtomicInteger serverNumberExecutions; //循环执行次数

    private static Logger log = LoggerFactory.getLogger(RoundRobinFiveRule.class);

    public RoundRobinFiveRule() {
        nextServerCyclicCounter = new AtomicInteger(0);
        serverNumberExecutions = new AtomicInteger(1);
    }

    /**
     *
     * @param lb 负载均衡器
     * @param key
     * @return
     */
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            log.warn("没有负载均衡器");
            return null;
        }

        Server server = null;
        int count = 0;
        while (server == null && count++ < 10) {

            List<Server> reachableServers = lb.getReachableServers(); // 访问服务器
            List<Server> allServers = lb.getAllServers();   // 所有服务器
            int upCount = reachableServers.size();  // 访问服务器的数量
            int serverCount = allServers.size();    // 所有服务器的数量

            if ((upCount == 0) || (serverCount == 0)) {
                log.warn("负载均衡器中没有up服务器可用: " + lb);
                return null;
            }
            int nextServerIndex = nextServerCyclicCounter.get();
            int curIndex = serverNumberExecutions.get();
            if(curIndex < 5){
                serverNumberExecutions.set(incrementAndGetModulo(serverNumberExecutions));
            }else{
                nextServerIndex = incrementAndGetModulo(nextServerCyclicCounter);
                if(nextServerIndex > serverCount-1){
                    nextServerIndex = 0;
                }
                nextServerCyclicCounter.set(nextServerIndex);// 获取下一个服务器的index
                serverNumberExecutions.set(1);// 下一次从0开始
            }

            server = allServers.get(nextServerIndex);   // 获取访问的服务器

            if (server == null) {
                /* Transient. */
                Thread.yield(); // 把自己CPU执行的时间让掉，让自己或者其它的线程运行，注意是让自己或者其他线程运行，并不是单纯的让给其他线程。
                continue;
            }

            //检查当前轮询的Server是否是Alive并且准备好服务的状态
            if (server.isAlive() && (server.isReadyToServe())) {
                return (server);
            }

            // Next.
            server = null;
        }

        if (count >= 10) {
            log.warn("从负载平衡器中尝试10次后，没有可用的活动服务器: "+ lb);
        }
        return server;
    }

    /**
     * 受{@link AtomicInteger#incrementAndGet()}实现的启发。
     * 循环的内容是
     *   1.取得当前值
     *   2.计算+1后的值
     *   3.如果当前值还有效(没有被)的话设置那个+1后的值
     *   4.如果设置没成功(当前值已经无效了即被别的线程改过了), 再从1开始.
     * @return 下一个值。
     */
    private int incrementAndGetModulo(AtomicInteger counter) {
        for (;;) {
            int current = counter.get();
            int next = current + 1;
            if (counter.compareAndSet(current, next))
                return next;
        }
    }



    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}