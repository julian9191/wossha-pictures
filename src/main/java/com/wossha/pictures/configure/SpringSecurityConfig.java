package com.wossha.pictures.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import com.wossha.pictures.infrastructure.filters.JWTAuthorizationFilter;
import com.wossha.pictures.infrastructure.services.JWTService;

@Configuration
@PropertySources({
	@PropertySource("classpath:application.properties"),
	@PropertySource("${external.properties}")
})
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JWTService jwtService;
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**", "/pictures/static-picture/{uuid}").permitAll()
		.anyRequest().authenticated()
		.and()
		.cors().and()
		.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtService))
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}
}
