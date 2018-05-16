package br.com.rsbdev.starter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * Swagger basic configuration
 * 
 * @author Renato
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("br.com.rsbdev"))
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(this.apiInfo());       
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
        		.title("Starter REST API")
        		.description("Estrutura inicial do projeto")
        		.contact(new Contact("RSBdev", "https://rsbdev.com.br", "contato@rsbdev.com.br"))
        		.build();
   }
}
