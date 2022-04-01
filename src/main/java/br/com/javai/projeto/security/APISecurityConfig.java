package br.com.javai.projeto.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class APISecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private APIEntryPoint entryPoint;
	
	public void configure(HttpSecurity httpSec) throws Exception {
		httpSec.csrf().disable()
		.exceptionHandling().authenticationEntryPoint(entryPoint)
		.and()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/login").permitAll()
		.antMatchers(HttpMethod.GET, "/pedidos").permitAll()
		.antMatchers(HttpMethod.GET, "/pedidos/*").permitAll()
		.antMatchers(HttpMethod.POST, "/geolocalizacao").permitAll()
		.antMatchers(HttpMethod.GET, "/geolocalizacao/*").permitAll()
		.antMatchers(HttpMethod.POST, "/entregador").permitAll()
		.antMatchers(HttpMethod.PATCH, "/pedidos/*").permitAll()
		.antMatchers(HttpMethod.GET, "/v2/api-docs", "/configuration/ui", 
										"/swagger-resources/**",  "/configuration/security",
											"/swagger-ui.html", "/webjars/**").permitAll()
		.anyRequest().authenticated().and().cors();
		
		httpSec.addFilterBefore(new APIFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}