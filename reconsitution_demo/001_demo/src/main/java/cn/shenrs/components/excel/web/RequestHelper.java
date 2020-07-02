package cn.shenrs.components.excel.web;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 *
 * @author jialin
 * @since 2016-6-28
 */
public class RequestHelper {
	public static HttpServletRequest getRequest(){
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return req;
    }
	
	public static HttpServletResponse getResponse(){
        HttpServletResponse req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        return req;
    }
	
	public static HttpSession  getSession(){
		HttpSession session=  getRequest().getSession();
		return session;
    }
	
	public static String getRequestClientIp(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		String ip = request.getHeader("X-Forwarded-For");  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_CLIENT_IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
        if(ip != null && ip.length() > 15 && ip.indexOf(",") > 0){ //"***.***.***.***".length() = 15  
        	ip = ip.split(",")[0];    
        }  
        if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equalsIgnoreCase(ip)) {  
			try {
				InetAddress inet = InetAddress.getLocalHost();
				if(inet != null){
					ip = inet.getHostAddress();
				}
			} catch (Exception e) {
			}finally{
				ip = (ip == null || "0:0:0:0:0:0:0:1".equalsIgnoreCase(ip)) ? "127.0.0.1" : ip;
			}
        }
        return ip;  
    }
}
