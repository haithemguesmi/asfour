package tunisie.asfour.tn;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import tunisie.asfour.tn.dao.TaskRepository;
import tunisie.asfour.tn.entites.AppRole;
import tunisie.asfour.tn.entites.AppUser;
import tunisie.asfour.tn.entites.Task;
import tunisie.asfour.tn.service.AccountService;



@SpringBootApplication
public class AsfourApplication implements CommandLineRunner{

	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	private AccountService accountService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(AsfourApplication.class, args);
	}

	@Bean//l'oesque l'application demarge ,cette methode elle executé ,
	//le resultat retourné sa devient un bean spring->injecté par tout --> tout les classe
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void run(String... args) throws Exception {
		accountService.saveUser(new AppUser(null,"admin","1987",null));
		accountService.saveUser(new AppUser(null,"user","1234",null));
		accountService.saveRole(new AppRole(null,"ADMIN"));
		accountService.saveRole(new AppRole(null,"USER"));
		accountService.addRoleToUser("admin", "ADMIN");
		accountService.addRoleToUser("admin", "USER");
		accountService.addRoleToUser("user", "USER");
		
		
		Stream.of("T1","T2","T3").forEach(t->{
		taskRepository.save(new Task(null,t));
		});
		taskRepository.findAll().forEach(t->{
			System.out.println(t.getTaskName());
		});
		
	}

	
	
	
}

