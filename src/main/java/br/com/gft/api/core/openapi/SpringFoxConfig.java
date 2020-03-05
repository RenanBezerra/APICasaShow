package br.com.gft.api.core.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig implements WebMvcConfigurer{
	
	@Bean
	public Docket apiDocket(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
					.apis(RequestHandlerSelectors.basePackage("br.com.gft.api"))
					.paths(PathSelectors.any())
					.build()
				.apiInfo(apiInfo())
	
				.tags(new Tag("Espaço-Evento","Gerencia as casas"))
				.tags(new Tag("Evento","Gerencia os eventos"))
				.tags(new Tag("Usuarios","Gerencia os usuarios"));
		
	}
	 public ApiInfo apiInfo() {
		 return new ApiInfoBuilder()
				 .title("API_CASA_SHOW")
				 .description("API aberta para clientes e usuarios")
				 .version("1")
				 .contact(new Contact("Renan_Bezerra","https://www.stackoverflow.com","renanbg90@gmail.com"))
				 .build();
	 }
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("swagger-ui.html")
			.addResourceLocations("classpath:/META-INF/resources/");
			
			registry.addResourceHandler("/webjars/**")
			.addResourceLocations("classpath:/META-INF/resources/webjars/");
			
			
			
		}
	}
