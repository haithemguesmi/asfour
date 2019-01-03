package nantes.asfour.tn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import nantes.asfour.tn.entites.AppUser;

@Service
public class UserDetailsServiceImp  implements UserDetailsService{

	@Autowired
	private AccountService accoutService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user=accoutService.findUserByUserName(username);
		if(user==null)throw new UsernameNotFoundException(username);
		return null;
	}

}
