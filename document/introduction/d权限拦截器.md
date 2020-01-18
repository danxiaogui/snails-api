
# 权限拦截器
系统后台需要对所有的 http 请求进行权限过滤，允许白名单的请求可以直接访问系统资源，其他的则需要登陆后才可获取，以保证系统的安全性。

## HandlerInterceptor 简介
SpringWebMVC的处理器拦截器，类似于Servlet开发中的过滤器Filter，用于处理器进行预处理和后处理。

## 应用场景
- 日志记录，可以记录请求信息的日志，以便进行信息监控、信息统计等。
- 权限检查：如登陆检测，进入处理器检测是否登陆，如果没有直接返回到登陆页面。
- 性能监控：典型的是慢日志。

## HandlerInterceptor
```java
public interface HandlerInterceptor {

   /**
     * 预处理回调方法，实现处理器的预处理（如检查登陆），第三个参数为响应的处理器，自定义Controller
     * 返回值：true表示继续流程（如调用下一个拦截器或处理器）；false表示流程中断（如登录检查失败），不会继续调用其他的拦截器或处理器，此时我们需要通过response来产生响应；
   */
    boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception;

   /**
     * 后处理回调方法，实现处理器的后处理（但在渲染视图之前），此时我们可以通过modelAndView（模型和视图对象）对模型数据进行处理或对视图进行处理，modelAndView也可能为null。
   */
    void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception;

   /**
    * 整个请求处理完毕回调方法，即在视图渲染完毕时回调，如性能监控中我们可以在此记录结束时间并输出消耗时间，还可以进行一些资源清理，类似于try-catch-finally中的finally，但仅调用处理器执行链中
   */
    void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception;
}
```

## 实现自己的权限过滤器 AuthInterceptor
```java
package com.kuzank.snails.config;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // TODO 权限过滤
    }
}
```

## 将 AuthInterceptor 注册到系统中
```java
package com.kuzank.snails.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Resource
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 跨域拦截器
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/api/person/account/login", "/api/file/download/**")
                .order(0);

        super.addInterceptors(registry);
    }

}
```
