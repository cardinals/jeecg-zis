package org.jeecgframework.core.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class IpUtil {
	/**
	 * 获取登录用户IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		/*if (ip.equals("0:0:0:0:0:0:0:1")) {
			ip = "127.0.0.1";
		}*/
		return ip;
	}
						 
	public static String getIpAddress(HttpServletRequest request) {  
		         String ip = request.getHeader("x-forwarded-for");  
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
		        return ip;  
		   }  
	
	public final static String getIpAddress2(HttpServletRequest request)   {  
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址  
  
        String ip = request.getHeader("X-Forwarded-For");  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
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
        } else if (ip.length() > 15) {  
            String[] ips = ip.split(",");  
            for (int index = 0; index < ips.length; index++) {  
                String strIp = (String) ips[index];  
                if (!("unknown".equalsIgnoreCase(strIp))) {  
                    ip = strIp;  
                    break;  
                }  
            }  
        }  
        return ip;  
    }  
  
				
					  
public static String getIpAdrress(HttpServletRequest request) {
  String Xip = request.getHeader("X-Real-IP");
  String XFor = request.getHeader("X-Forwarded-For");
  if(StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
   //多次反向代理后会有多个ip值，第一个ip才是真实ip
   int index = XFor.indexOf(",");
   if(index != -1){
    return XFor.substring(0,index);
   }else{
    return XFor;
   }
  }
  XFor = Xip;
  if(StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
   return XFor;
  }
  if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
   XFor = request.getHeader("Proxy-Client-IP");
  }
  if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
   XFor = request.getHeader("WL-Proxy-Client-IP");
  }
  if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
   XFor = request.getHeader("HTTP_CLIENT_IP");
  }
  if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
   XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
  }
  if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
   XFor = request.getRemoteAddr();
  }
  return XFor;
 }

   /**
	* 获取本地IP列表（针对多网卡情况）
	*
	* @return
	*/
	public static List<String> getLocalIPList() {
       List<String> ipList = new ArrayList<String>();
       try {
           Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
           NetworkInterface networkInterface;
           Enumeration<InetAddress> inetAddresses;
           InetAddress inetAddress;
           String ip;
           while (networkInterfaces.hasMoreElements()) {
               networkInterface = networkInterfaces.nextElement();
               inetAddresses = networkInterface.getInetAddresses();
               while (inetAddresses.hasMoreElements()) {
                   inetAddress = inetAddresses.nextElement();
                   if (inetAddress != null && inetAddress instanceof Inet4Address) { // IPV4
                       ip = inetAddress.getHostAddress();
                       ipList.add(ip);
                       //System.out.println("获取本机IP："+ip);
                   }
               }
           }
       } catch (SocketException e) {
           e.printStackTrace();
       }
       return ipList;
	}
}
