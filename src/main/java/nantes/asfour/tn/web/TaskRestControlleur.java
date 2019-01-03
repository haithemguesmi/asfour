package nantes.asfour.tn.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import nantes.asfour.tn.dao.TaskRepository;
import nantes.asfour.tn.entites.Task;

@RestController
public class TaskRestControlleur {

	@Autowired
	private TaskRepository taskRepository;
	//ou bien constcuteur avec parameter
    /*
	public TaskRestControlleur(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	*/
	
	@GetMapping("/tasks")
	public List<Task> listTasks(){
		return taskRepository.findAll();
	}
	
	@PostMapping("/tasks")
	public Task save (@RequestBody Task t) {
		return taskRepository.save(t);
	}
}
