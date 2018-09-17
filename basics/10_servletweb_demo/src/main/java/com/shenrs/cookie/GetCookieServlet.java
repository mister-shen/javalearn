package com.shenrs.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * @author shenrs
 * @Description 获取cookie
 * @create 2018-09-17 10:16
 **/
@WebServlet("/GetCookieServlet")
public class GetCookieServlet extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + ": " + URLDecoder.decode(cookie.getValue()) + " 过期时间：" + cookie.getMaxAge());
        }
        System.out.println("Cookie获取成功....");
    }
}
