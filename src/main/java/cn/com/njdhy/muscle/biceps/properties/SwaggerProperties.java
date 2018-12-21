package cn.com.njdhy.muscle.biceps.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Swagger 配置文件获取信息
 *
 * @author jason.hu
 * @
 */
@Component
@ConfigurationProperties(prefix = "swagger")
@Getter
@Setter
public class SwaggerProperties {

    /**
     * swagger读取接口的所在包名
     */
    private String api;

    /**
     * swagger接口的主标题
     */
    private String title;

    /**
     * swagger接口的描述信息
     */
    private String description;

    /**
     * swagger接口的版本
     */
    private String version;

    /**
     * swagger接口的访问地址
     */
    private String termsOfServiceUrl;

    /**
     * 接口的联系人
     */
    private String name;

    /**
     * 联系人的url地址
     */
    private String url;

    /**
     * 联系人的邮箱
     */
    private String email;
}
