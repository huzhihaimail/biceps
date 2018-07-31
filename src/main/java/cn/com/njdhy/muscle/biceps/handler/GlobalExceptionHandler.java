
package cn.com.njdhy.muscle.biceps.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * <类功能简述> 统一异常处理
 * <功能详细描述>
 *
 * @author 胡志海
 * @version V0.0.1-SNAPSHOT
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 统一处理RuntimeException
     *
     * @param request 对象
     * @param e       异常信息
     * @return 统一页面处理
     * @throws Exception 异常
     */
    @ExceptionHandler(value = {RuntimeException.class})
    public String dealRuntimeException(HttpServletRequest request, Exception e) throws Exception {

        // 记录日志
        // TODO: 2018/3/8
        return "";
    }

    /**
     * 统一处理RuntimeException
     *
     * @param request 对象
     * @param e       异常信息
     * @return 统一页面处理
     * @throws Exception 异常
     */
    @ExceptionHandler(value = {Exception.class})
    public String dealException(HttpServletRequest request, Exception e) throws Exception {

        // 记录日志
        return "";

    }

}
