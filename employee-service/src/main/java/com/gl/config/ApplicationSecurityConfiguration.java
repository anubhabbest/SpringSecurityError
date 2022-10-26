package com.gl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gl.service.DomainUserDetailsService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Autowired
	private DomainUserDetailsService domainUserDetailsService;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		// auth.inMemoryAuthentication().withUser("ramesh").password(passwordEncoder().encode("ramesh")).roles("USER").and().withUser("suresh").password(passwordEncoder().encode("suresh")).roles("USER","ADMIN");
		auth.userDetailsService(domainUserDetailsService).passwordEncoder(passwordEncoder());
	
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.headers().frameOptions().disable();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/employees/**").hasAnyRole("USER","ADMIN")
			.antMatchers(HttpMethod.POST,  "/api/employees/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.DELETE,  "/api/employees/**").hasRole("ADMIN")
			.antMatchers("/h2-console/**").permitAll()
			.and()
			.formLogin().and().httpBasic()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().csrf().disable();
			
	}

	
}
