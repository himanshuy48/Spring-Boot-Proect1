package com.iot.app.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;

import com.google.common.base.Predicates;
import com.google.common.collect.Lists;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Class SwaggerConfig.
 * 
 * @author ujjwal kumar sinha
 */

/*
 * @Configuration
 * 
 * @EnableSwagger2 public class SwaggerConfig {
 * 
 * @Bean public Docket api() { return new
 * Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any
 * ()) .paths(Predicates.not(PathSelectors.regex("/error.*"))).build().apiInfo(
 * apiInfo()); }
 * 
 * 
 * private ApiInfo apiInfo() { return new ApiInfo("Iot connextion app",
 * "API doc for Iot connextion app Services", "0.1", null, new
 * Contact("Ujjwal Kumar Sinha", null, "ujjwal.clix@gmail.com"), null,
 * null,Collections.emptyList()); }
 * 
 * private ApiKey apiKey() { return new ApiKey("authkey", "Authorization",
 * "header"); } }
 */

/*
 * 2
 * 
 * @Configuration
 * 
 * @EnableSwagger2
 * 
 * @Import(springfox.bean.validators.configuration.
 * BeanValidatorPluginsConfiguration.class) public class SwaggerConfig {
 * 
 * public static final String AUTHORIZATION_HEADER = "Authorization"; public
 * static final String DEFAULT_INCLUDE_PATTERN = "/"; private final Logger log =
 * LoggerFactory.getLogger(SwaggerConfig.class);
 * 
 * @Bean public Docket swaggerSpringfoxDocket() { //
 * log.debug("Starting Swagger"); // Contact contact = new Contact( //
 * "Matyas Albert-Nagy", // "https://justrocket.de", // "matyas@justrocket.de");
 * // // List<VendorExtension> vext = new ArrayList<>(); // ApiInfo apiInfo =
 * new ApiInfo( // "Backend API", //
 * "This is the best stuff since sliced bread - API", // "6.6.6", //
 * "https://justrocket.de", // contact, // "MIT", // "https://justrocket.de", //
 * vext);
 * 
 * Docket docket = new Docket(DocumentationType.SWAGGER_2) // .apiInfo(apiInfo)
 * 
 * .apiInfo(ApiInfo.DEFAULT) .forCodeGeneration(true)
 * .genericModelSubstitutes(ResponseEntity.class)
 * .ignoredParameterTypes(Pageable.class)
 * .ignoredParameterTypes(java.sql.Date.class)
 * .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
 * .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
 * .directModelSubstitute(java.time.LocalDateTime.class, Date.class) //
 * .securityContexts(Lists.newArrayList(securityContext()))
 * .securitySchemes(Lists.newArrayList(apiKey()))
 * .useDefaultResponseMessages(false);
 * 
 * // docket = docket.select() // .paths(regex(DEFAULT_INCLUDE_PATTERN)) //
 * .build(); // watch.stop(); // log.debug("Started Swagger in {} ms",
 * watch.getTotalTimeMillis()); return docket; }
 * 
 * 
 * private ApiKey apiKey() { return new ApiKey("Authorization",
 * AUTHORIZATION_HEADER, "Authorization"); }
 * 
 * // private SecurityContext securityContext() { // return
 * SecurityContext.builder() // .securityReferences(defaultAuth()) //
 * .forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN)) // .build(); // }
 * 
 * List<SecurityReference> defaultAuth() { AuthorizationScope authorizationScope
 * = new AuthorizationScope("global", "accessEverything"); AuthorizationScope[]
 * authorizationScopes = new AuthorizationScope[1]; authorizationScopes[0] =
 * authorizationScope; return Lists.newArrayList( new
 * SecurityReference("Authorization", authorizationScopes)); } }
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2).ignoredParameterTypes(AuthenticationPrincipal.class).select()
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
				.paths(PathSelectors.any()).build().securitySchemes(Lists.newArrayList(apiKey()))
				.securityContexts(Arrays.asList(securityContext())).apiInfo(apiInfo());

	}

	private ApiInfo apiInfo() {
		return new ApiInfo("Iot connextion app", "API doc for Iot connextion app Services", "0.1", null,
				new Contact("Ujjwal Kumar Sinha", null, "ujjwal.clix@gmail.com"), null, null, Collections.emptyList());
	}

	private ApiKey apiKey() {
		return new ApiKey("apiKey", "Authorization", "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("apiKey", authorizationScopes));
	}
}