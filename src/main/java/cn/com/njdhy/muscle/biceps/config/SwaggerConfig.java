package cn.com.njdhy.muscle.biceps.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jason.hu
 * @date 2018-07-13
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        List apiKeys = new ArrayList<ApiKey>();
        apiKeys.add(apiKey());

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(springfox.documentation.builders.PathSelectors.regex("/.*"))
                .build()
                .apiInfo(apiInfo()).securitySchemes(apiKeys);
    }

    private ApiKey apiKey() {
        return new ApiKey("x-auth-token", "token", "header");
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "海航项目REST API",
                "Dev environment",
                "v1",
                "",
                new Contact("", "", ""),
                "",
                ""
        );
        return apiInfo;
    }
}
