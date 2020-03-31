package com.web.circle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.web.circle.repository.UserRepo;
import com.web.circle.service.CircleJwtAuthenFilter;
import com.web.circle.service.CircleJwtAuthorFilter;
import com.web.circle.service.UserPDS;

@Configuration
@EnableWebSecurity
public class CircleConfig extends WebSecurityConfigurerAdapter {

	private UserPDS userPDS;
	private UserRepo userR;
	
	private PasswordEncoder encoder;
	
	public CircleConfig(UserPDS userPDS, UserRepo userR) {
		this.userPDS = userPDS;
		this.userR = userR;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
	       .csrf().disable()
	       .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	       .and()
		       .addFilter(new CircleJwtAuthenFilter(authenticationManager()))
		       .addFilter(new CircleJwtAuthorFilter(authenticationManager(),  this.userR))
		       .authorizeRequests()
		       .antMatchers("/index").permitAll()
		       .antMatchers("/dashboard/**").authenticated()
		       .antMatchers("/admin/**").hasAnyRole("SUPER_USER","ADMIN")
		       .antMatchers("/management/**").hasAnyRole("ROLE_SUPER_USER","ADMIN")
		       .antMatchers("/api/public/management/*").hasRole("SUPER_USER")
		       .antMatchers("/api/public/admin/*").hasRole("ADMIN")
		   .and()
		       .formLogin()
		       .loginProcessingUrl("/signin")
		       .loginPage("/login").permitAll()
		       .usernameParameter("txtUsername")
		       .passwordParameter("txtPassword")
	       .and()
	       		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
	       .and() 
		       .rememberMe().tokenValiditySeconds(2592000).key("Circle").rememberMeParameter("checkRememberMe") // 2592000 is equal to 30 days.
		       .userDetailsService(userPDS);
		
		  /*http
          // remove csrf and state in session because in jwt we do not need them
          .csrf().disable()
          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          .and()
          // add jwt filters (1. authentication, 2. authorization)
          .addFilter(new CircleJwtAuthenFilter(authenticationManager()))
          .addFilter(new CircleJwtAuthorFilter(authenticationManager(),  this.userR))
          .authorizeRequests()
          // configure access rules
          .antMatchers(HttpMethod.POST, "/login").permitAll()
          .antMatchers("/api/public/management/*").hasRole("SUPER_USER")
          .antMatchers("/api/public/admin/*").hasRole("ADMIN")
          .anyRequest().authenticated(); */
		  
		
		/*http.authorizeRequests()
	        .antMatchers("/index.html").permitAll()
	        .antMatchers("/profile/**").authenticated()
	        .antMatchers("/admin/**").hasRole("ADMIN")
	        .antMatchers("/management/**").hasAnyRole("ADMIN", "MANAGER")
	        .antMatchers("/api/public/test1").hasAuthority("ACCESS_TEST1")
	        .antMatchers("/api/public/test2").hasAuthority("ACCESS_TEST2")
	        .antMatchers("/api/public/users").hasRole("ADMIN")
	        .and()
	        .formLogin()
	        .loginProcessingUrl("/signin")
	        .loginPage("/login").permitAll()
	        .usernameParameter("txtUsername")
	        .passwordParameter("txtPassword")
	        .and()
	        .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
	        .and()
	        .rememberMe().tokenValiditySeconds(2592000).key("mySecret!").rememberMeParameter("checkRememberMe");*/

	}
	
	@Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userPDS);

        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
