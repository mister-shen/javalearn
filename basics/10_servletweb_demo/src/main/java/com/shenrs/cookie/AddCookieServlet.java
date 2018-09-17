package com.shenrs.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author shenrs
 * @Description 添加cookie
 * @create 2018-09-17 10:16
 **/
@WebServlet("/AddCookieServlet")
public class AddCookieServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 添加内容包含中文，需要进行编码
        Cookie cookie = new Cookie("shenrs", URLEncoder.encode("测试添加cookie...."));

        Cookie cookie2 = new Cookie("shenrs2", URLEncoder.encode("测试添加cookie2...."));

        resp.addCookie(cookie);
        resp.addCookie(cookie2);
        System.out.println("Cookie添加成功....");
    }
}
