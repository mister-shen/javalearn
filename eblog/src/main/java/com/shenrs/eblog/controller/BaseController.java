package com.shenrs.eblog.controller;

import com.shenrs.eblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    @Autowired
    HttpServletRequest req;


    @Autowired
    PostService postService;
}