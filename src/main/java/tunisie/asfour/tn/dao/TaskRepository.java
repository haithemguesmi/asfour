package tunisie.asfour.tn.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tunisie.asfour.tn.entites.Task;



public interface TaskRepository extends JpaRepository<Task, Long> {

}
