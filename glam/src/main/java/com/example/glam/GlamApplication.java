package com.example.glam;

import com.example.glam.filters.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class GlamApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlamApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<AuthFilter> setAuthFilter() {
		// This block uses the AuthFilter class to verify if a valid JWT token is provided in the Auth header.
		FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
		AuthFilter authFilter = new AuthFilter();
		registrationBean.setFilter(authFilter);

		// This block specifies which routes must be protected from non-authenticated users.
		registrationBean.addUrlPatterns("/api/products");
		registrationBean.addUrlPatterns("/api/users/add");//enroll

		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean setCorsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();

		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:4200");
		config.addAllowedOrigin("http://localhost:3000");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);

		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);

		return bean;
	}

}
