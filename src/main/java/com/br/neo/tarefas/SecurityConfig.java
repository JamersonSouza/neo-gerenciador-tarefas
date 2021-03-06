package com.br.neo.tarefas;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.WebJarsResourceResolver;

@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private DataSource dataSource;
	
//	As consultas estão no arquivo properties.
	
	@Value("${spring.queries.users-query}")
	private String userQuery;
	
	@Value("${spring.queries.roles-query}")
	private String roleQuery;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth
		.jdbcAuthentication()
		.usersByUsernameQuery(userQuery)
		.authoritiesByUsernameQuery(roleQuery)
		.dataSource(dataSource)
		.passwordEncoder(passwordEncoder);
		
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/login", "/webjars/**").permitAll()
			.antMatchers("/registration").permitAll()
			.anyRequest()
				.authenticated()
					.and().csrf().disable() //estudar mais este assunto para trabalhar com token
					.formLogin()
						.loginPage("/login").failureUrl("/login?error=true").defaultSuccessUrl("/")
							.usernameParameter("email").passwordParameter("senha")
					.and().logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");		
						
					
	}
//	METODO ABAIXO NÃO FUNCIONA, FOI NECESSÁRIO USAR CDN DO BOOTSTRAP POIS O SPRING SECURITY
//	ESTA BLOQUEANDO O ACESSO AO WEBJARS. AINDA NÃO DESCOBRI COMO RESOLVER
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/webjars/**, /resources/**");
	}
	
}
