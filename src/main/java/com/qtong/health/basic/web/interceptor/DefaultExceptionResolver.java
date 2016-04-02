package com.qtong.health.basic.web.interceptor;

import com.qtong.health.basic.utils.HttpUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by ZML on 2016/3/22.
 */
@ControllerAdvice
public class DefaultExceptionResolver extends SimpleMappingExceptionResolver {

    private static final Logger logger = Logger.getLogger(DefaultExceptionResolver.class);

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {


        String remoteAddress = HttpUtils.getRealRemoteAddr(request);
        String targetMethod = null;
        logger.error("REMOTE ADDRESS: " + remoteAddress);
        logger.error("REQUEST URL: " + request.getRequestURL());
        logger.error("PARAMETERMAP: " + request.getParameterMap().toString());
        logger.error("EXCEPTION:", ex);
        return super.doResolveException(request, response, handler, ex);
    }

     /*   String viewName = determineViewName(ex, request);

        if (viewName != null) {// JSP格式返回
            if (!(request.getHeader("accept").indexOf("application/json") > -1
                    || (request.getHeader("X-Requested-With") != null
                    && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
                // 如果不是异步请求
                // Apply HTTP status code for error views, if specified.
                // Only apply it if we're processing a top-level request.
                Integer statusCode = determineStatusCode(request, viewName);
                if (statusCode != null) {
                    applyStatusCodeIfPossible(request, response, statusCode);
                }
                return getModelAndView(viewName, ex, request);
            }
        } else {
            // JSON格式返回
            try {
                PrintWriter writer = response.getWriter();
                Map map = new HashMap();
                map.put("success", false);
                map.put("massage", ex.getMessage());
                writer.write(JSONUtils.toJSONString(map));
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        return null;*/
    /*    *//**
     * 所有可捕获的异常都由此抛出
     *
     * @param request
     * @param ex
     * @return 所有运行时异常都由此抛出，比如空指针，数据库连接丢失，数组越界等，
     * <p/>
     * <p/>
     * 都是由于服务器运行环境或者代码健壮性不够引起的异常
     * @param request
     * @param ex
     * @return 所有运行时异常都由此抛出，比如空指针，数据库连接丢失，数组越界等，
     * <p/>
     * <p/>
     * 都是由于服务器运行环境或者代码健壮性不够引起的异常
     * @param request
     * @param ex
     * @return 所有运行时异常都由此抛出，比如空指针，数据库连接丢失，数组越界等，
     * <p/>
     * <p/>
     * 都是由于服务器运行环境或者代码健壮性不够引起的异常
     * @param request
     * @param ex
     * @return
     *//*
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ModelAndView handleException(HttpServletRequest request, HttpServletResponse response,
                                        Exception ex) throws IOException {
        //获取调用客户端的ip
        String remoteAddress = HttpUtils.getRealRemoteAddr(request);
        String targetMethod = null;
        try {
            targetMethod = methodNameResolver.getHandlerMethodName(request);
        } catch (NoSuchRequestHandlingMethodException e) {
            e.printStackTrace();
        }
        logger.warn("REMOTE ADDRESS: " + remoteAddress);
        logger.warn("TARGET METHOD: " + targetMethod);
        logger.warn("PARAMETERMAP: " + request.getParameterMap().toString());
        logger.warn("EXCEPTION:", ex);
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }

    *//**
     * 所有运行时异常都由此抛出，比如空指针，数据库连接丢失，数组越界等，
     * <p>
     * <p>
     * 都是由于服务器运行环境或者代码健壮性不够引起的异常
     *
     * @param request
     * @param ex
     * @return
     *//*
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        //获取调用客户端的ip
        String remoteAddress = HttpUtils.getRealRemoteAddr(request);
        String targetMethod = null;
        try {
            targetMethod = methodNameResolver.getHandlerMethodName(request);
        } catch (NoSuchRequestHandlingMethodException e) {
            e.printStackTrace();
        }
        logger.error("REMOTE ADDRESS: " + remoteAddress);
        logger.error("TARGET METHOD: " + targetMethod);
        logger.error("PARAMETERMAP: " + request.getParameterMap().toString());
        logger.error("EXCEPTION:", ex);
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }*/
}
