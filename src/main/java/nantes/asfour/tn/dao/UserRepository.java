package nantes.asfour.tn.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import nantes.asfour.tn.entites.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long>{
	
	public AppUser findByUsername(String username);

}
