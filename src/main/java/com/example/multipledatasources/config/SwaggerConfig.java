package com.example.multipledatasources.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiInfo() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.multipledatasources.controller"))
                .paths(regex("/api.*"))
                .build()
                .apiInfo(getApiInfo())
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, getCustomisedResponses())
                .globalResponseMessage(RequestMethod.POST, getCustomisedResponses())
                .globalResponseMessage(RequestMethod.PUT, getCustomisedResponses())
                .globalResponseMessage(RequestMethod.DELETE, getCustomisedResponses())
                .globalResponseMessage(RequestMethod.PATCH, getCustomisedResponses());
    }
    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("User REST API's")
                .description("API's for all user details info")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("\"https://www.apache.org/licenses/LICENSE-2.0\\\"\"")
                .contact(new Contact("John Thompson", "https://springframework.guru/about/", "john@springfrmework.guru"))
                .build();
    }
    public List<ResponseMessage> getCustomisedResponses() {
      List<ResponseMessage> responseMessageList = new ArrayList<>();
      responseMessageList.add(new ResponseMessageBuilder().code(200).message("User found").build());
      responseMessageList.add(new ResponseMessageBuilder().code(201).message("User successfully added").build());
      responseMessageList.add(new ResponseMessageBuilder().code(204).message("User removed").build());
      responseMessageList.add(new ResponseMessageBuilder().code(500).
              message("Server has crashed")/*.responseModel(new ModelRef("error"))*/.build());
      responseMessageList.add(new ResponseMessageBuilder().code(400).message("A user with the given id already exist").build());
      responseMessageList.add(new ResponseMessageBuilder().code(404).message("User not found").build());
      return responseMessageList;
    }
}
