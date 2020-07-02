package com.shenrs.eblog.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.shenrs.eblog.entity.Category;
import com.shenrs.eblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * 项目启动时调用
 * ApplicationRunner: 项目启动时调用，ServletContextAware：获取servletContext
 */
@Component
public class ContextStartup implements ApplicationRunner, ServletContextAware{
    @Autowired
    private CategoryService categoryService;

    private ServletContext servletContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Category> categorys = categoryService.list(new QueryWrapper<Category>().eq("status", 0));
        servletContext.setAttribute("categorys" ,categorys);
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
