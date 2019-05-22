package com.shenrs.springcloud.service;

import com.shenrs.springcloud.eneites.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author shenrs
 * @Description 统一处理某一个服务提供接口的异常
 * @create 2019-05-21 14:52
 **/
@Component/*必须添加该注解*/
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService>{
    @Override
    public DeptClientService create(Throwable cause) {
        return new DeptClientService() {
            @Override
            public Dept get(Long id) {
                return new Dept().setDeptno(id)
                        .setDname("该ID:" + id + "没有对应的信息。Consumer客户端提供的降级信息，此刻服务Provider已经关闭")
                        .setDbSource("no this database in MySQL");
            }

            @Override
            public List<Dept> list() {
                return null;
            }

            @Override
            public boolean add(Dept dept) {
                return false;
            }

            @Override
            public Object discovery() {
                return null;
            }
        };
    }
}
