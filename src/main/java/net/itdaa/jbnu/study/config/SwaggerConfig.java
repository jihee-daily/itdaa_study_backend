<<<<<<<< Updated upstream:src/main/java/net/itdaa/backend/study/config/SwaggerConfig.java
package net.itdaa.backend.study.config;
========
package net.itdaa.jbnu.study.config;
>>>>>>>> Stashed changes:src/main/java/net/itdaa/jbnu/study/config/SwaggerConfig.java

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
/**
 * Swagger-Ui 사용을 위한 설정을 담당하는 클래스
 */
public class SwaggerConfig {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Java Backend Hands-on (By Itdaa & JBNU)")
                .description("백엔드개발자 직무 체험 클래스")
                .version("1.0")
                .build();
    }

    private Set<String> getConsumeContentTypes() {
        Set<String> consumes = new HashSet<>();
        consumes.add("application/json;charset=UTF-8");
        consumes.add("application/x-www-form-urlencoded");

        return consumes;
    }

    private Set<String> getProduceContentTypes() {
        Set<String> produce = new HashSet<>();
        produce.add("application/json;charset=UTF-8");

        return produce;
    }

    @Bean
    public Docket commonApi() {
        return new Docket(DocumentationType.SWAGGER_2).consumes(getConsumeContentTypes())
                .produces(getProduceContentTypes())
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/**"))
                .build();
    }
}
