package com.kuzank.snails.config;

import com.kuzank.snails.core.Result;
import com.kuzank.snails.jpa.HttpLogJpa;
import com.kuzank.snails.model.HttpLog;
import com.kuzank.snails.service.IdentityService;
import com.kuzank.snails.util.RequestUtil;
import com.kuzank.snails.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 权限拦截器
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    private static final String REQUEST_TIME_BEGIN = "requestTimeBegin";

    @Autowired
    HttpLogJpa httpLogJpa;
    @Autowired
    IdentityService identityService;

    private static final Logger LOG = LoggerFactory.getLogger(AuthInterceptor.class);

    /**
     * 在业务处理器处理请求之前被调用，可以进行编码、安全控制等处理；
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = RequestUtil.getToken(request);

        request.setAttribute(REQUEST_TIME_BEGIN, System.currentTimeMillis());

        // 判断是否登陆
        if (identityService.isLogin(token)) {
            return true;
        } else {
            ResponseUtil.writeJson(response, Result.oflost("用户未登陆！", 401));
            return false;
        }
    }

    /**
     * 在业务处理器处理请求执行完成后，生成视图之前执行后处理（调用了Service并返回ModelAndView，但未进行页面渲染），有机会修改ModelAndView
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

        HttpLog httpLog = null;
        Map identity = identityService.getByToken(RequestUtil.getToken(request));
        if (identity != null) {
            httpLog = HttpLog.of(identity.get("id").toString(), request);
        } else {
            httpLog = HttpLog.of(null, request);
        }

        if (request.getAttribute(REQUEST_TIME_BEGIN) != null) {
            Long startTime = (Long) request.getAttribute(REQUEST_TIME_BEGIN);
            double elapsedtime = System.currentTimeMillis() - startTime;
            httpLog.setElapsedtime(elapsedtime);
        }

        httpLogJpa.save(httpLog);
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用，也就是说视图渲染已经完毕或者调用者已经拿到结果
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

}
