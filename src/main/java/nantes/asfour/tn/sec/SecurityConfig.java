package nantes.asfour.tn.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import nantes.asfour.tn.entites.AppRole;
import nantes.asfour.tn.entites.AppUser;

@Configurable
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*-auth.inMemoryAuthentication()
		.withUser("admin").password("admin").roles("ADMIN","USER")
		.and()
		.withUser("user").password("user").roles("USER");
		*/
		/*auth.jdbcAuthentication()
		.usersByUsernameQuery("")//decrit la requette de jdbc"*from pour recupré le username"
		.authoritiesByUsernameQuery("recupére le role de utilisateur ");*/
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(bCryptPasswordEncoder);//hachage de mot de passe , encodage -->cryptage 
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		//
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		//http.formLogin().loginPage("/login");
		//http.formLogin();
		http.authorizeRequests().antMatchers("/login/**","/register/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/tasks/**").hasAuthority("ADMIN");
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
	}
}
