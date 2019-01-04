package nantes.asfour.tn.web;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import nantes.asfour.tn.entites.AppUser;
import nantes.asfour.tn.service.AccountService;

@RestController
public class AccountRestControlleur {

	@Autowired
	private AccountService accountService;
	
	@PostMapping("register")
	public AppUser registre(@RequestBody RegisterForm userForm) {
		if (!userForm.getPassword().equals(userForm.getRepawword())) throw new RuntimeException("You maust confirm your password");
		
		//si le mdp existe deja
		AppUser user=accountService.findUserByUserName(userForm.getUsername());
		if(user!=null) throw new RuntimeException("this user alredy esists ");
		
		AppUser appUser=new AppUser();
		appUser.setUsername(userForm.getUsername());
		appUser.setPassword(userForm.getPassword());
		//role par defaut
		accountService.addRoleToUser(userForm.getUsername(),"USER");
		
		accountService.saveUser(appUser);
		return appUser;
	}
	
	
	
}
