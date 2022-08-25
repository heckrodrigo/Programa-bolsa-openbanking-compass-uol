package uol.compass.Programabolsaopenbanking.config;


import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 *
 * @author Rodrigo Heck
 * @version 1.0
 * @since 2022-08-26
 * 
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

	@Bean
	public Docket productApi() {

		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("uol.compass"))
				.paths(PathSelectors.any()).build().apiInfo(metaInfo());
	}

	private ApiInfo metaInfo() {
    	   ApiInfo apiInfo = new ApiInfo(
    			   "Produtos API REST",
    			   "API REST de cadastro de produtos.",
    			   "1.0",
    			   "Terms od Service",
    			   new Contact ("Rodrigo Heck", "https://heckrodrigo.github.io/",
    			   				"rodrigoheck@gmail.com"),
    			   "Apache License Version 2.0",
    			   "https://www.apache.org/license.html", new ArrayList<VendorExtension>()
    			   
    			   
    			   );
	   			return apiInfo;
    	   
    	   
       }

}
