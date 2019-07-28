package com.smartshop.configuration.security;

import com.smartshop.service.UserPrincipleDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserPrincipleDetailsService userPrincipleDetailsService;
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(userPrincipleDetailsService);
		return daoAuthenticationProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http)
			throws Exception {
		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/login").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.antMatchers("/api/user").hasRole("MANAGER")
				.antMatchers("/api/user/profile").hasRole("USER")
				.antMatchers("/api/user/verify").permitAll()
				.antMatchers("/api/user/login").permitAll()
				.antMatchers("/api/product").hasAnyRole("MARKETING", "ADMIN")
				.antMatchers(HttpMethod.GET, "/api/product").permitAll()
				.antMatchers("/api/img/**").permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.httpBasic();
	}



	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/**")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.httpBasic();
	}*/

}






