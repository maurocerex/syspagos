package com.system.pagos.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public static final String URL = "https://www.systempagos.com/";

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.system.pagos"))
                .paths(PathSelectors.ant("/v1/**"))
                .build()
                .groupName("v1")
                .apiInfo(getApiInfo("1"))
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, getCustomizedResponseMessages())
                .globalResponseMessage(RequestMethod.POST, getCustomizedResponseMessages())
                .globalResponseMessage(RequestMethod.PUT, getCustomizedResponseMessages())
                .globalResponseMessage(RequestMethod.DELETE, deleteCustomizedResponseMessages())
                .globalResponseMessage(RequestMethod.PATCH, getCustomizedResponseMessages());
    }

    private ApiInfo getApiInfo(String version) {
        return new ApiInfo(
                "Sistema de Pagos",
                "Sistema de Pagos Versión de Prueba",
                "v".concat(version),
                URL,
                new Contact("Mauricio Cortés", URL, "mauricio.cortes@meltsan.com"),
                "Licencia -->  Dev",
                URL,
                Collections.emptyList()

        );
    }

    private List<ResponseMessage> getCustomizedResponseMessages() {
        List<ResponseMessage> responseMessages = new ArrayList<>();
        responseMessages.add(new ResponseMessageBuilder().code(200).message("Correcto").build());
        responseMessages.add(new ResponseMessageBuilder().code(201).message("Creado").build());
        responseMessages.addAll(customizedResponseMessages());
        return responseMessages;
    }

    private List<ResponseMessage> deleteCustomizedResponseMessages() {
        List<ResponseMessage> responseMessages = new ArrayList<>();
        responseMessages.add(new ResponseMessageBuilder().code(204).message("Sin Contenido").build());
        responseMessages.addAll(customizedResponseMessages());
        return responseMessages;
    }

    private List<ResponseMessage> customizedResponseMessages() {
        List<ResponseMessage> responseMessages = new ArrayList<>();
        responseMessages.add(new ResponseMessageBuilder().code(400).message("Peticion Incorrecta").build());
        responseMessages.add(new ResponseMessageBuilder().code(401).message("Token Incorrecto").build());
        responseMessages.add(new ResponseMessageBuilder().code(403).message("Forbidden").build());
        responseMessages.add(new ResponseMessageBuilder().code(404).message("Recurso No Encontrado").build());
        responseMessages.add(new ResponseMessageBuilder().code(409).message("Conflicto en Peticion").build());
        responseMessages.add(new ResponseMessageBuilder().code(500).message("Error en el Servidor").build());
        return responseMessages;
    }
}
