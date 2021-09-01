package com.qa.choonz.config;

import com.qa.choonz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Adapted from javainuse.com/webseries/sping-security-jwt
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{


	@Autowired
	UserService userDetailsService;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		// httpSecurity.csrf().disable()
		// 	.authorizeRequests()
		// 	.antMatchers("/", "/tracks", "/artists", "/playlists", "/albums",
		// 																	 "/**/read", "/login", "/signup", "/h2", "/auth",
		// 																	 "/index.html",
		// 																	 "/login.html", "/register", "/h2").permitAll()
		// 	.anyRequest().authenticated();
		httpSecurity.authorizeRequests()
			.antMatchers("/**/create", "/**/update", "/**/delete").hasRole("USER")
			.antMatchers("/", "/tracks", "/artists", "/playlists", "/albums", "/css/**",
									 "/img/**", "/js/**", "/header.html",
									 "/index.html", "/artists.html",
									 "/**/read", "/login", "/signup", "/h2", "/auth",
									 "/register", "/h2").permitAll()
			//			.antMatchers().permitAll()
			.anyRequest().authenticated()
			// .and().exceptionHandling()
			// .authenticationEntryPoint(unauthorizedHandler)
			.and()
			.formLogin().loginPage("/login").permitAll()
			.and()
			.logout().permitAll()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
