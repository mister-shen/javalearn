package com.shenrs.lifecycle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shenrs
 * @Description servlet生命周期
 * @create 2018-09-17 9:51
 **/
@WebServlet("/LifecycleServelt")
public class LifecycleServelt extends HttpServlet{
    private int i = 0;

    /**
     * 无参构造函数，说明ServeltLifecycle是一个单例
     */
    public LifecycleServelt() {
        System.out.println("LifecycleServelt对象创建");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("servlet初始化.....");
    }

    /**
     * servlet单例，线程不安全
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("servlet处理请求....");
        /**
         * 为防止多个线程共享同一个成员变量，添加synchronize锁
         */
        try {
            resp.setCharacterEncoding("utf-8");// 内容编码，防止出现中文乱码
            resp.setContentType("text/html;charset=utf-8");
            synchronized (LifecycleServelt.class){
                i++;
                resp.getWriter().write("第" + i + "次调用服务");
            }
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        System.out.println("servlet销毁....");
    }
}
