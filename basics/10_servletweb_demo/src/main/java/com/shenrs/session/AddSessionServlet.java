package com.shenrs.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author shenrs
 * @Description 添加session
 * @create 2018-09-17 10:46
 **/
@WebServlet("/AddSessionServlet")
public class AddSessionServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);// 默认不传为true，没有sessionid 我会创建session 否则
        session.setAttribute("username", "shenrs");
    }
}
