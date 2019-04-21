package com.shenrs.service;

import com.shenrs.bean.Employee;
import com.shenrs.mapper.EmployeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;


/**
 * @author shenrs
 * @Description
 * @create 2019-03-30 15:56
 **/
@CacheConfig(cacheNames = "emp")/*全局设置cache的属性*/
@Service
public class EmployeeService {
    private Logger log = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeMapper employeeMapper;


    /**
     * 将方法的运行结果进行缓存；以后再有相同的数据，直接从缓存中获取，不调用方法。
     * CacheManager管理多个Cache组件，对缓存真正的CRUD操作都在Cache组件中，每一个缓存组件有自己唯一一个名字。
     * 缓存原理：
     *  1.自动配置类：org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration
     *  2.缓存的配置类:
     *      org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration
     *      org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration
     *      org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration
     *      org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration
     *      org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration
     *      org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration
     *      org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
     *      org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration
     *      org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration
     *      org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration
     *  3.那个配置类默认生效 SimpleCacheConfiguration
     *  4.给容器中注册了一个cacheManager:ConcurrentMapCacheManager
     *  5.可以获取和创建ConcurrentMapCache类型的缓存组件，他的作用将数据保存在ConcurrentMap中；
     *
     *  运行的流程：
     *      1.方法运行之前，先去查询cache(缓存组件)，按照cacheName指定的名字获取；
     *      （CacheManager先获取相应的缓存）,第一次获取缓存如果没有Cache组件会自动创建。
     *      2.去Cache中查询缓存的内容，使用一个key,默认就是方法的参数；
     *          key是按照某种策略生成的；默认是使用keyGenerator生成的，默认使用SimpleKeyGenerator生成key；
 *                  SimpleKeyGenerator生成key的默认策略：
     *                  如果没有参数key=SimpleKey.EMPTY;
     *                  如果有一个参数key=param;
     *                  如果有多个参数key=new SimpleKey(params);
     *      3.没有查到缓存就调用目标方法；
     *      4.将目标方法的结果进行缓存。
     *      @Cacheable标注的方法执行之前先来检查缓存中有没有这数据，默认按照参数key的值作为key去查询缓存。
     *      如果没有就运行方法，并将结果放入缓存。
     *
     *      核心：
     *          1)使用CacheManager【ConcurrentMapCacheManager】按照名字得到Cache【ConcurrentMapCache】组件。
     *          2)key使用keyGenerator生成的，默认是SimpleKeyGenerator。
     *
     * 几个属性:
     *      cacheNames/value: 指定缓存组件的名字。将方法的返回结果放在哪个缓存中，是数据的方式，可以指定多个缓存；
     *      key: 缓存数据使用的key;可以用它来指定。默认是使用方法参数的值  1-方法值得返回
     *          编写SpEL; #id:参数id的值， #a0 #b0 #root.args[0]
     *          @Cacheable(value = {"emp"}, key = "#root.methodName+'['+#id+']'"):key的名字:getEmpById[1]
     *
     *      keyGenerator: key的生成器；可以自己指定key的生成器的组件id
     *          key/keyGenerator: 二选一使用
     *          @Cacheable(value = {"emp"}, keyGenerator = "myKeyGenerator") :自定义key生成策略
     *      cacheManager: 指定缓存管理器；或者cacheResolver指定获取解析器
     *      condition: 指定符合条件的情况下才缓存。
     *          @Cacheable(value = {"emp"}, keyGenerator = "myKeyGenerator", condition = "#a0>1")
     *          第一个参数大于1才执行缓存
     *      unless： 否定缓存；当unless指定的条件为true, 方法的返回值就不会被缓存，可以获取到结果进行判断。
     *          unless = "#result == null"
     *          unless = "#p0 == 2":如果第一个参数的值是2不缓存。
     *          @Cacheable(value = {"emp"}, keyGenerator = "myKeyGenerator", condition = "#a0>1", unless = "#p0 == 2")
     *      sync: 是否使用异步模式
     *
     * @param id
     * @return
     */
    @Cacheable(/*value = {"emp"}*/)
    public Employee getEmpById(Integer id){
        log.info("查询" + id + "号员工");
        return employeeMapper.getEmpById(id);
    }

    /**
     * @CachePut:既调用方法，又更新缓存数据
     * 修改数据库的某个数据，同时又更新缓存。
     * 运行时机：
     *  1.先调用目标方法
     *  2.将目标方法的结果缓存起来
     *
     * 测试步骤：
     *      1.查询1号员工，查到的结果会放在缓存中
     *      2.以后查询还是之前的结果
     *      3.更新1号员工
     *          将方法的返回值也放进缓存。
     *          key:传入的employee对象，值：返回的employee对象
     *      4.查询1号员工
     *          应该是更新后的员工
     *              key = "#employee.id":使用传入的参数的员工id
     *              key = "#result.id":使用返回后的id
     *                  @Cacheable的key是不能用的。
     * @param employee
     */
    @CachePut(/*value = "emp",*/ key = "#employee.id")
    public Employee updateEmp(Employee employee){
        employeeMapper.updateEmp(employee);
        log.info("更新" + employee.getId() + "号员工");
        return employee;

    }

    /**
     * @CacheEvict：缓存清除
     *  key：指定要清除的数据
     *  allEntries = true：指定清除这个缓存中的所有数据
     *  beforeInvocation = false：缓存的数据是否在方法执行之前执行
     *      默认代表在方法执行之后执行，如果出现异常缓存就不会清除。
     * @param id
     */
    @CacheEvict(/*value = "emp",*/ key = "#id"/*, allEntries = true*/) // allEntries清空emp下所有的缓存
    public void deleteEmp(Integer id){
        employeeMapper.deleteEmp(id);
        log.info("删除" + id + "号员工");
    }

    /**
     * @Caching定义复杂的缓存规则
     * @param lastName
     * @return
     */
    @Caching(
            cacheable = {
                    @Cacheable(/*value = "emp",*/ key = "#lastName")
            },
            put = {
                    @CachePut(/*value = "emp", */key = "#result.id"),
                    @CachePut(/*value = "emp",*/ key = "#result.gender"),
                    // 返回的属性值不能为空，否则会出现异常
                    // java.lang.IllegalArgumentException: Null key returned for cache operation (maybe you are using named params on classes without debug info?)
            }
    )
    public Employee getEmpByLastName(String lastName){
        return employeeMapper.getEmpByLastName(lastName);
    }

}
