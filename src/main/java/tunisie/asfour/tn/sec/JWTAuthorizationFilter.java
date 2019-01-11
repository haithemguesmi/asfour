package tunisie.asfour.tn.sec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import tunisie.asfour.tn.constante.SecurityConstants;

public class JWTAuthorizationFilter  extends  OncePerRequestFilter{

	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String jwt=request.getHeader(SecurityConstants.HEADER_STRING);
		if(jwt== null || !jwt.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			filterChain.doFilter(request, response);
			return;
		}
		Claims claims=Jwts.parser()
				//validation signature 
		.setSigningKey(SecurityConstants.SECRET)
		.parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_PREFIX,""))
		.getBody();
		
		String username=claims.getSubject();
		ArrayList<Map<String, String>> roles=(ArrayList<Map<String,String>>) claims.get("roles");
		Collection<GrantedAuthority> authorities =new ArrayList<>();
		roles.forEach(r->{
			authorities.add(new SimpleGrantedAuthority(r.get("authority")));
		});
		UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(username,null,authorities);
		
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		filterChain.doFilter(request, response);

	}

}
