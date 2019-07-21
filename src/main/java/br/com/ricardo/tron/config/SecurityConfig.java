package br.com.ricardo.tron.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.ricardo.tron.security.AppUserDetailsService;

@EnableWebSecurity
@ComponentScan(basePackageClasses = AppUserDetailsService.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService; 

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		
		//auth.inMemoryAuthentication()
			//.withUser("admin").password("admin").roles("CADASTRAR_USUARIO");
	}
	
	public void configure(WebSecurity web) throws Exception{
		web.ignoring()
		.antMatchers("/layout/**")
		.antMatchers("/images/**");

		
	}
		
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/usuarios/novo").hasRole("CADASTRAR_USUARIO") 
				.antMatchers("/usuarios").hasRole("CONSULTAR_USUARIO")
				.antMatchers("/funcionarios/novo").hasRole("CADASTRAR_FUNCIONARIO")
				.antMatchers("/funcionarios").hasRole("CONSULTAR_FUNCIONARIO")				
				.antMatchers("/clientes/novo").hasRole("CADASTRAR_CLIENTE")
				.antMatchers("/clientes").hasRole("CONSULTAR_CLIENTE")				
				.antMatchers("/fornecedores/novo").hasRole("CADASTRAR_FORNECEDOR")
				.antMatchers("/fornecedores").hasRole("CONSULTAR_FORNECEDOR")				
				.antMatchers("/os/nova").hasRole("CADASTRAR_OS")
				.antMatchers("/os").hasRole("CONSULTAR_OS")				
				.antMatchers("/servicos/novo").hasRole("CADASTRAR_SERVICO")
				.antMatchers("/servicos").hasRole("CONSULTAR_SERVICO")				
				.antMatchers("/produtos/novo").hasRole("CADASTRAR_PRODUTO")
				.antMatchers("/produtos").hasRole("CONSULTAR_PRODUTO")				
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
				.csrf().disable()
			//	.and()
				.logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.and()
				.exceptionHandling().accessDeniedPage("/403")
				.and()
				.sessionManagement()
					.maximumSessions(1)
					.expiredUrl("/login");

	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
