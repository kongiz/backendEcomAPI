package com.backend.ecom.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	// Add any additional configuration if needed
	// For example, you can add view resolvers, resource handlers, etc.
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/api/images/**")
		.addResourceLocations("file:///D:/Android_Projects/backendEcomAPI/uploads/images/");

	}
	

}



//public void addResourceHandlers(ResourceHandlerRegistry registry) {
//    String imagePath = Paths.get("D:/Android_Projects/backendEcomAPI/uploads/images")
//                            .toUri()
//                            .toString();
//
//    registry.addResourceHandler("/api/images/**")
//            .addResourceLocations(imagePath)
//            .setCachePeriod(3600); // cache optional
//}
//}
