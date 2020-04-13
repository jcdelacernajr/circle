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

import com.web.circle.repository.UserRepository;
import com.web.circle.service.CircleJwtAuthenFilter;
import com.web.circle.service.CircleJwtAuthorFilter;
import com.web.circle.service.UserPrincipalDetailsService;

/**
 * @author Juanito C. Dela Dela Cerna Jr. March 2020
 * 
 */
@Configuration
@EnableWebSecurity
public class CircleConfiguration extends WebSecurityConfigurerAdapter {

	private UserPrincipalDetailsService userPDS;
	private UserRepository userR;
	
	public CircleConfiguration(UserPrincipalDetailsService userPDS, UserRepository userR) {
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
		       .antMatchers("/signup").permitAll()
		       .antMatchers("/dashboard").hasAnyRole("HUMAN_RESOURCES","INVENTORY_MANAGEMENT","COMPANY_PROFILING")
		       .antMatchers("/maintenance/**").hasAnyRole("SUPER_USER","ADMIN")
		       .antMatchers("/admin/**").hasAnyRole("SUPER_USER","ADMIN")
		       .antMatchers("/management/**").hasAnyRole("SUPER_USER","ADMIN")
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
	}
	
	@Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userPDS);
        return daoAuthenticationProvider;
    }
	

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
