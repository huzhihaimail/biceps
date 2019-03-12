package cn.com.njdhy.muscle.biceps.intercepter;

import ch.qos.logback.classic.Logger;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通用拦截器
 *
 * @author huzh
 * @date jackson.hu
 */
@Component
public class CommonInterceptor extends HandlerInterceptorAdapter {

    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行
     *
     * @param request      请求对象
     * @param response     响应对象
     * @param handler      处理
     * @param modelAndView 视图层对象
     * @throws Exception 异常对象
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("拦截器拦截到了请求" + request.getRequestURL());
    }
}
