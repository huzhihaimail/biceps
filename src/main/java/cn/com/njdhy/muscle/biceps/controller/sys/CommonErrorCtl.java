
package cn.com.njdhy.muscle.biceps.controller.sys;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * <类功能简述> springboot自身的404、500页面比较丑陋,覆盖/error请求地址，修改默认404、500页面
 * <功能详细描述>
 *
 * @author 胡志海
 * @version V0.0.1-SNAPSHOT
 */
@Controller
public class CommonErrorCtl implements ErrorController {


    @Override
    public String getErrorPath() {
        return "/error";
    }

    /**
     * 修改默认404y页面
     *
     * @return
     */
    @RequestMapping("/error")
    public String deal404Error(HttpServletRequest request) {

        // 记录日志
        // todo

        return "html/error/404.html";
    }

}
