package tunisie.asfour.tn.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tunisie.asfour.tn.entites.AppUser;

@Service
public class UserDetailsServiceImp  implements UserDetailsService{

	@Autowired
	private AccountService accoutService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user=accoutService.findUserByUserName(username);
		if(user==null)throw new UsernameNotFoundException(username);
		Collection<GrantedAuthority> authorites=new ArrayList<>();
		user.getRoles().forEach(r->{
			authorites.add(new SimpleGrantedAuthority(r.getRoleName()));
		});
		
		return new User(user.getUsername(),user.getPassword(),authorites);
	}

}
