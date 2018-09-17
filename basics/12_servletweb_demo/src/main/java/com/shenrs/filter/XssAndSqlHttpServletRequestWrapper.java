package com.shenrs.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 防止XSS攻击
 */
public class XssAndSqlHttpServletRequestWrapper extends HttpServletRequestWrapper {
    HttpServletRequest request;
	public XssAndSqlHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	@Override
	public String getParameter(String name) {
		String value = request.getParameter(name);
		System.out.println("name:" + name + "," + value);
		if (!StringUtils.isEmpty(value)) {
			// 转换Html
			value = StringEscapeUtils.escapeHtml4(value);
		}
		return value;
	}
}