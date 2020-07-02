package com.shenrs.eblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 分类controller
 */
@Controller
public class PostController extends BaseController {

    /**
     * 跳转到分类明细页面
     * 注：:\d* 表示只能输入数字
     * @param id
     * @return
     */
    @GetMapping("/category/{id:\\d*}")
    public String category(@PathVariable(name = "id" ) Long id) {
        req.setAttribute("currentCategoryId", id);
        return "post/category";
    }

    /**
     * 跳转到分类明细页面
     * 注：:\d* 表示只能输入数字
     * @param id
     * @return
     */
    @GetMapping("/post/{id:\\d*}")
    public String detail(@PathVariable(name = "id" ) Long id) {
        return "post/detail";
    }
}
