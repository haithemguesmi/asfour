package nantes.asfour.tn;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import nantes.asfour.tn.dao.TaskRepository;
import nantes.asfour.tn.entites.Task;



@SpringBootApplication
public class AsfourApplication implements CommandLineRunner{

	@Autowired
	TaskRepository taskRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(AsfourApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Stream.of("T1","T2","T3").forEach(t->{
		taskRepository.save(new Task(null,t));
		});
		taskRepository.findAll().forEach(t->{
			System.out.println(t.getTaskName());
		});
		
	}

	
	
	
}

