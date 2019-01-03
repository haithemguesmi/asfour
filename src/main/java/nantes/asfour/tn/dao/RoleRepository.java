package nantes.asfour.tn.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import nantes.asfour.tn.entites.AppRole;

public interface RoleRepository extends JpaRepository<AppRole, Long>{

	public AppRole findByRoleName(String roleName);
	
}
