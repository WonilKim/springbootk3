package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig { // extends WebSecurityConfigurerAdapter {
	
	@Autowired
	BoardUserDetailsService boardUserDetailsService;
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean 
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		
		security.authorizeHttpRequests(auth->{
//			auth.requestMatchers("/").permitAll()
//				.requestMatchers("/member/**").authenticated()
//				.requestMatchers("/manager/**").hasRole("MANAGER")
//				//.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
//				.requestMatchers("/admin/**").hasRole("ADMIN");
			
			auth.requestMatchers("/member/**").authenticated()
//				.requestMatchers("/manager/**").hasRole("MANAGER")
				.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
				.requestMatchers("/admin/**").hasRole("ADMIN")	// /admin과 모든 하위 경로는 ADMIN에게만 허용하겠다.
				.anyRequest().permitAll();		// 그 외의 모든 요청은 허가 한다
			
		});
		
//		security.csrf().disable();
		security.csrf(csrf->csrf.disable());	// JS에서 호출가능
		security.cors(cors->cors.disable());	// react에서 호출가능
		
//		security.formLogin();
		security.formLogin(form->{
			form.loginPage("/login").defaultSuccessUrl("/loginSuccess");
		});
		
		security.exceptionHandling(ex->ex.accessDeniedPage("/accessDenied"));
		security.logout(log->{
			log.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.logoutSuccessUrl("/login");
		});
		
		security.userDetailsService(boardUserDetailsService);
		
		return security.build();
	}
	
	@Autowired
	public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("member")
			.password("{noop}abcd")	// {noop} 함호화 방법. no operation 
			.roles("MEMBER");
		auth.inMemoryAuthentication().withUser("manager").password("{noop}abcd").roles("MANAGER");
		auth.inMemoryAuthentication().withUser("admin").password("{noop}abcd").roles("ADMIN");
	}

//	@Autowired
//	private BoardUserDetailsService boardUserDetailsService;
//
//	@Override
//	protected void configure(HttpSecurity security) throws Exception {
//		security.authorizeRequests().antMatchers("/").permitAll();
//		security.authorizeRequests().antMatchers("/member/**").authenticated();
//		security.authorizeRequests().antMatchers("/manager/**").hasRole("MANAGER");
//		security.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
//
//		security.csrf().disable();
//		security.formLogin().loginPage("/login").defaultSuccessUrl("/loginSuccess", true);
//		security.exceptionHandling().accessDeniedPage("/accessDenied");
//		security.logout().invalidateHttpSession(true).logoutSuccessUrl("/login");
//
//		security.userDetailsService(boardUserDetailsService);
//
//	}
//	
//	@Bean 
//	public PasswordEncoder passwordEncoder() {
//		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//	}

}
