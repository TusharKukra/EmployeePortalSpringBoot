package com.nagarro.javafreshertraining.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class Config extends WebSecurityConfigurerAdapter{
	
	@Bean
	public UserDetailsService getUserDetailService() {
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider  authenticationProvider() {
		 DaoAuthenticationProvider  daoAuthenticationProvider = new  DaoAuthenticationProvider();
		 daoAuthenticationProvider.setUserDetailsService(this.getUserDetailService());
		 daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		 
		 return  daoAuthenticationProvider;
	}
	
	
	/* Now Configuring our Methods */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	
	/* Configuring Routes */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// Only Admin role can authorize or user which have USER_ROLE & and also we allow all other urls and form based login and CSRF Disable
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/user/**").hasRole("USER")
		.antMatchers("/**").permitAll().and().formLogin()
		.loginPage("/signin")
		.loginProcessingUrl("/dologin")
		.defaultSuccessUrl("/user/index")
		.failureUrl("/login-fail")
		.and().csrf().disable();
	}
	
	
}
