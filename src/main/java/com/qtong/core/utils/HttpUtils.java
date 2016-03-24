package com.qtong.core.utils;

import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by ZML on 2016/3/22.
 */
public class HttpUtils {

    private static final int MAXTHREADS = 500;
    private static final int TIMEOUT = 3000;
    private static final Semaphore semaphore = new Semaphore(MAXTHREADS);
    private static ExecutorService exec = Executors.newCachedThreadPool();
    private static HttpRequestExecutor executor = null;
    private static Stack<HttpPost> postStack = null;
    private static Stack<HttpGet> getStack = null;
    private static CloseableHttpAsyncClient client = null;
    private static Logger logger = Logger.getLogger(HttpRequestExecutor.class);

    private HttpUtils() throws IOReactorException {

        postStack = new Stack<>();

        getStack = new Stack<>();

        ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor();

        PoolingNHttpClientConnectionManager cm = new PoolingNHttpClientConnectionManager(ioReactor);

        cm.setMaxTotal(MAXTHREADS);

        cm.setDefaultMaxPerRoute(MAXTHREADS);

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(TIMEOUT).setConnectTimeout(TIMEOUT).build();// 设置请求和传输超时时间
        client = HttpAsyncClients.custom()//自定义httpclient
                .setConnectionManager(cm)
                .setDefaultRequestConfig(requestConfig)
                .setRedirectStrategy(new DefaultRedirectStrategy() {
                    // 自动跟踪主机重定向
                    public boolean isRedirected(HttpRequest request,
                                                HttpResponse response, HttpContext context) throws ProtocolException {
                        boolean isRedirect = false;

                        isRedirect = super.isRedirected(request, response,
                                context);

                        if (!isRedirect) {
                            int responseCode = response.getStatusLine()
                                    .getStatusCode();
                            if (responseCode == 301 || responseCode == 302) {
                                return true;
                            }
                        }
                        return isRedirect;
                    }
                }).setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy()).build();
        client.start();
    }

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

    /**
     * 单例模式设计这个类，以保证只初始化一次，节省系统资源
     *
     * @return
     */
    public static synchronized HttpRequestExecutor getInstance() {
        if (executor == null) {
            executor = new HttpRequestExecutor();
        }
        return executor;
    }

    public HttpGet createHttpGet(String url, Map... args) {
        Assert.isTrue(isURL(url), "该字符串不是url");
        StringBuffer sb = new StringBuffer(url);
        if (url.indexOf('?') < 0) {// 如果原来的url中只有action没有参数
            sb.append("?");
        } else {
            sb.append("&");
        }
        if (args.length > 0 && args[0] != null) {
            Map params = new HashMap();
            for (Map param : args) {
                params.putAll(param);
            }
            for (Object key : params.keySet()) {
                sb.append(key + "=" + params.get(key) + "&");
            }
        }
        HttpGet get = null;
        synchronized (getStack) {
            if (getStack.size() < 1) {
                get = new HttpGet();
            } else {
                get = getStack.pop();
            }
        }

        get.setURI(URI.create(sb.toString()));
        return get;
    }

    public HttpPost createHttpPost(String url, Map... args) {

        Assert.isTrue(isURL(url), "该字符串不是url");

        HttpPost post = null;

        synchronized (postStack) {
            if (postStack.size() < 1) {
                post = new HttpPost();
            } else {
                post = postStack.pop();
            }
        }
        post.setURI(URI.create(url));
        if (args.length > 0 && args[0] != null) {
            Map params = new HashMap();
            for (Map param : args) {
                params.putAll(param);
            }

            logger.debug(params);
            List<NameValuePair> list = new ArrayList<>();
            for (Object key : params.keySet()) {
                list.add(new BasicNameValuePair((String) key,
                        params.get(key) == null ? null : params.get(key)
                                .toString()));
            }
            //ful 20151204 加utf-8编码
            try {
                post.setEntity(new UrlEncodedFormEntity(list, "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return post;
    }

    public boolean isURL(String str) {
        if (str == null)
            return false;
        // 转换为小写
        String url = str.toLowerCase();
        String regex = "^((https|http|ftp|rtsp|mms)?://)"
                + "(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" // ftp的user@
                + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
                + "|" // 允许IP和DOMAIN（域名）
                + "([0-9a-z_!~*'()-]+\\.)*" // 域名- www.
                + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." // 二级域名
                + "[a-z]{2,6})" // first level domain- .com or .museum
                + "(:[0-9]{1,4})?" // 端口- :80
                + "((/?)|" // a slash isn't required if there is no file name
                + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
        return url.matches(regex);

    }

    /**
     * @param url
     * @param args
     * @return
     */
    public String post(String url, Map... args) {

        HttpPost post = createHttpPost(url, args);
        return handleResponse(post);
    }

    public String get(final String url, final Map... args) {
        HttpGet get = createHttpGet(url, args);
        return handleResponse(get);
    }

    private String handleResponse(final HttpRequestBase method) {

        try {
            semaphore.acquire();
            HttpResponse response = client.execute(method, null).get();
            if (response != null && HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                return EntityUtils.toString(response.getEntity());
            }
        } catch (ExecutionException | InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
            if (method instanceof HttpGet) {
                getStack.push((HttpGet) method);
            } else {
                postStack.push((HttpPost) method);
            }
            semaphore.release();
        }
        return null;

    }

    @Override
    protected void finalize() throws Throwable {
        client.close();
        exec.shutdown();
        super.finalize();
    }
}
