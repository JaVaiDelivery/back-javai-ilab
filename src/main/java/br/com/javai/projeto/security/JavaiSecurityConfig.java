package br.com.javai.projeto.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class JavaiSecurityConfig extends WebSecurityConfigurerAdapter {
	public void configure(HttpSecurity httpSec) throws Exception {
		httpSec.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.GET, "/pedidos*").permitAll()
		.anyRequest().authenticated().and().cors();
		
		httpSec.addFilterBefore(new LoginFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}