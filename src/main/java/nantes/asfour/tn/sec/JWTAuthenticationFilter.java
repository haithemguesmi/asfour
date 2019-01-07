package nantes.asfour.tn.sec;

import java.io.IOException;
import java.util.Date;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties.Jwk;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import nantes.asfour.tn.constante.SecurityConstants;
import nantes.asfour.tn.entites.AppUser;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	
	private AuthenticationManager authenticationManager;
	
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager=authenticationManager;
	}


	//recupére les format json et stock dans la classe appuser
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
	 AppUser appUser=null;
	 //recupére les format json et stock dans la classe appuser
	 try {
		appUser=new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		throw new RuntimeException();
	}
	 
	 System.out.println("***************");
	 System.out.println("username:"+appUser.getUsername());
	 System.out.println("passs:"+appUser.getPassword());
	 
		return authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword()));
				
	}
	
	
	//dans cette methode on genere le token 
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		User springUser=(User) authResult.getPrincipal();
		String jwt=Jwts.builder() 
		.setSubject(springUser.getUsername())
		.setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
		//L'ALGORTHME UTLISEÉ POUR GENERARE LE TOKEN
		.signWith(SignatureAlgorithm.HS256,SecurityConstants.SECRET)
		.claim("roles", springUser.getAuthorities())
		.compact();
		
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX+jwt);
		
	}
	
	
}
