package tunisie.asfour.tn.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tunisie.asfour.tn.entites.AppRole;

public interface RoleRepository extends JpaRepository<AppRole, Long>{

	public AppRole findByRoleName(String roleName);
	
}
