package br.com.erudio.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate.Param;

import br.com.erudio.serialization.converter.YamlJackson2HttpMessageConverter;

@Configuration //diz ao spring boot que quando ao subir a aplicação está classe deve ser lida
public class WebConfig implements WebMvcConfigurer{

	private static final MediaType MEDIA_TYPE_APPLICATION_YML = MediaType.valueOf("application/x-yaml");
	
	@Value("${cors.originPatterns:default}")
	private String corsOriginPatterns = "";
	
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new YamlJackson2HttpMessageConverter());
	}
	

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		var allowedOrigins = corsOriginPatterns.split(",");
		registry.addMapping("/**")//equivale a todas as rotas da API
		//.allowedMetods("GET","POST","PUT")
		.allowedMethods("*")
		.allowedOrigins(allowedOrigins)
		.allowCredentials(true);
	}



	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		
		// Via QUERY Param. http://localhost:8080/api/person/v1/7?mediaType=json
		/*
		configurer.favorParameter(true)
		.parameterName("mediaType").ignoreAcceptHeader(true).useRegisteredExtensionsOnly(false)
		.defaultContentType(MediaType.APPLICATION_JSON)
		.mediaType("json", MediaType.APPLICATION_JSON)
		.mediaType("json", MediaType.APPLICATION_XML);
		*/
		
		// Via Header Param. http://localhost:8080/api/person/v1
		configurer.favorParameter(false)
		.ignoreAcceptHeader(false)
		.useRegisteredExtensionsOnly(false)
		.defaultContentType(MediaType.APPLICATION_JSON)
			.mediaType("json", MediaType.APPLICATION_JSON)
			.mediaType("json", MediaType.APPLICATION_XML)
			.mediaType("x-yaml", MEDIA_TYPE_APPLICATION_YML);
		
	}
	
	
	
	

}
