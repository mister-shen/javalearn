package com.shenrs.controller;

import com.shenrs.dao.DepartmentDao;
import com.shenrs.dao.EmployeeDao;
import com.shenrs.entities.Department;
import com.shenrs.entities.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author shenrs
 * @Description
 * @create 2018-11-07 16:56
 **/
@Controller
public class EmployeeController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;
    /**
     * 查询所有员工返回列表页面
     * @param model
     * @return
     */
    @GetMapping("/emps")
    public String list(Model model) {
        // 放在请求域中
        model.addAttribute("emps", employeeDao.getAll());
        // thymeleaf默认就会拼串
        // classpath:/templates/xxxx.html
        return "emp/list";
    }

    /**
     * 跳转员工添加页面
     * @param id
     * @return
     */
    @GetMapping("/emp")
    public String toAddPage(Model model){
        // 查出所有的部门，在页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    /**
     * 添加员工
     * SpringMVC自动将请求参数和入参对象的属性进行一一绑定；
     * 要求请求参数的名字和javaBean入参的对象的属性名一样。
     * @return
     */
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        logger.info("保存的员工信息："+employee);
        employeeDao.save(employee);
        // 来到员工列表页面
        // redirect: 表示重定向到一个地址， /代表当前项目路径
        // forward: 表示转发到一个地址
        return "redirect:/emps";
    }

    /**
     * 修改页面，查出当前员工信息，在页面回显
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model){
        Employee employee = employeeDao.get(id);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("emp", employee);
        model.addAttribute("depts", departments);
        return "/emp/add";
    }

    /**
     * 修改员工信息
     * @param employee
     * @return
     */
    @PutMapping("/emp")
    public String updateEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @DeleteMapping("/emp/{id}")
    public String delEmp(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }

}
