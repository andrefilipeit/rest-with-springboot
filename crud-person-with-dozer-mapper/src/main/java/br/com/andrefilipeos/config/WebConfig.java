package br.com.andrefilipeos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

		// Example via EXTENSION. GET example: localhost:8080/api/person/v1.xml or json

		/*
		 * configurer.favorParameter(false).ignoreAcceptHeader(false).defaultContentType
		 * (MediaType.APPLICATION_JSON) .mediaType("json",
		 * MediaType.APPLICATION_JSON).mediaType("xml", MediaType.APPLICATION_XML);
		 */

		
		// Example with QUERY PARAM localhost:8080/api/person/v1?mediaType=xml or ?mediaType=json 
		
		/*
		 * configurer.favorPathExtension(false).favorParameter(true).parameterName(
		 * "mediaType").ignoreAcceptHeader(true)
		 * .useRegisteredExtensionsOnly(false).defaultContentType(MediaType.
		 * APPLICATION_JSON) .mediaType("json",
		 * MediaType.APPLICATION_JSON).mediaType("xml", MediaType.APPLICATION_XML);
		 */
		
		
		// Example with HEADERS - Key: Accept / Value: application/xml or /json
		
		configurer.favorPathExtension(false).favorParameter(false).ignoreAcceptHeader(false)
		.useRegisteredExtensionsOnly(false).defaultContentType(MediaType.APPLICATION_JSON)
		.mediaType("json", MediaType.APPLICATION_JSON).mediaType("xml", MediaType.APPLICATION_XML);

	}

}
