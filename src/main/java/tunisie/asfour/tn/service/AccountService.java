package tunisie.asfour.tn.service;

import tunisie.asfour.tn.entites.AppRole;
import tunisie.asfour.tn.entites.AppUser;

public interface AccountService {

	public AppUser saveUser(AppUser user);

	public AppRole saveRole(AppRole role);

	public void addRoleToUser(String username,String roleName);
	
    public 	AppUser findUserByUserName(String username);
    
    
}
