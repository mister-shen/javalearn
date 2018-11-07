package com.shenrs.controller;

import com.shenrs.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author shenrs
 * @Description
 * @create 2018-11-07 16:56
 **/
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

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
}
