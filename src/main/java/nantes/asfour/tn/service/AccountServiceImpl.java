package nantes.asfour.tn.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nantes.asfour.tn.dao.RoleRepository;
import nantes.asfour.tn.dao.UserRepository;
import nantes.asfour.tn.entites.AppRole;
import nantes.asfour.tn.entites.AppUser;


@Service
@Transactional
public class AccountServiceImpl  implements AccountService{

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	@PreAuthorize("ROLE_ADMIN")
	public AppUser saveUser(AppUser user) {
	    String hasPw=bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(hasPw);
		return userRepository.save(user);
	}

	@Override
	public AppRole saveRole(AppRole role) {
		
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		AppRole role=roleRepository.findByRoleName(roleName);
		AppUser user=userRepository.findByUsername(username);
		user.getRoles().add(role);
		
	}

	@Override
	public AppUser findUserByUserName(String username) {
		
		return userRepository.findByUsername(username);
	}

}
