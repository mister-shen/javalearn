
package com.shenrs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "ImgFilter",/*设置filter名称*/
		   urlPatterns = "/imgs/*" /*设置拦截地址*/
)
public class ImgFilter implements Filter {


	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("filter初始化....");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("进入filter....");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String referer = req.getHeader("referer");
		String serverName = req.getServerName();
		if(referer == null || !referer.contains(serverName)){// 如果访问来源不是本网站地址 拦截
			req.getRequestDispatcher("error.png").forward(req, resp);// 转发到错误提示
			return;
		}

		// 放行
		chain.doFilter(req, resp);

	}

	public void destroy() {
		System.out.println("filter销毁....");
	}
}
