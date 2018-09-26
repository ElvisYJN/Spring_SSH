package com.xinhuanet.app.tool;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

public class IpTool {

    /**
     * 获得客户端真实IP地址,此方法可获得开启代理网络环境真实IP,但也易于伪造IP
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        //如果代理开启了forwarded_for 选项，上述代码一般能搞定
        //如果是匿名代理，那是神仙也难救
       String ip = request.getHeader("x-forwarded-for");
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = request.getHeader("Proxy-Client-IP");
       }
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = request.getHeader("WL-Proxy-Client-IP");
       }
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = request.getRemoteAddr();
           if(ip.equals("127.0.0.1")){
               //根据网卡取本机配置的IP
               InetAddress inet=null;
               try {
                   inet = InetAddress.getLocalHost();
               }catch (UnknownHostException ex) {
                   ex.printStackTrace();
               }
               ip= inet.getHostAddress();
           }

       }
       //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
       //X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 192.168.1.100
       //用户真实IP为： 192.168.1.110

       if(ip!=null && ip.indexOf(",")>0){
           String[] str = ip.split(",");  
           if(str!=null && str.length>1){  
               ip = str[0];  
           }  
       }

       return ip;
    } 
}
