package com.qtong.core.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * Created by ZML on 2016/3/22.
 */
public class HttpUtils {

    public static String getRealRemoteAddr(HttpServletRequest request) {
        String ipAddress = null;
        //ipAddress =request.getRemoteAddr();   
        ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1")) {
                //根据网卡取本机配置的IP   
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }

        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    public static void printJSONResponse(HttpServletResponse response, int httpStatus, Map params) {
        response.setContentType("application/json");
        response.setStatus(httpStatus == 0 ? 200 : httpStatus);
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.print(JSONUtils.toJSONString(params));
            /*在有重定向需求的请求中，如跳转到500页面，执行close之后，页面不会跳转*/
            // printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printJSONResponse(HttpServletResponse response, Map params) {
        printJSONResponse(response, 200, params);
    }
}
