package cn.com.wjqhuaxia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Description: swagger配置类
 */
@Configuration
// 开启swagger2
// 选择不同的环境启用 swagger 以下两种方式，推荐第一种
// @Profile({"dev","test"})
// @ConditionalOnProperty(name = "swagger.enable", havingValue = "true")
public class SwaggerConfig {
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("用户权限项目") // 设置项目名
				.apiInfo(apiInfo())
				.pathMapping("/")	 // 设置api根路径
				.select()				 // 初始化并返回一个API选择构造器
				.apis(RequestHandlerSelectors.basePackage("cn.com.wjqhuaxia"))	// swagger api扫描的路径
				.paths(PathSelectors.any())	// 设置路径筛选
				.build();					// 构建
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("user-auth服务")
				.description("提供用户权限功能服务接口")
				.license("")
				.licenseUrl("")
				.termsOfServiceUrl("")
				.version("1.0.0")
				.build();
	}
}
