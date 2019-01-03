package nantes.asfour.tn.entites;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
public class AppUser {

	@Id @GeneratedValue
	Long id;
	@Column(unique=true)
	String username;
	String password;
	
	@ManyToMany(fetch=FetchType.EAGER)
	Collection<AppRole> roles =new ArrayList<AppRole>();
	
	
}
