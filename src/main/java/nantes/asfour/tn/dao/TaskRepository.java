package nantes.asfour.tn.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import nantes.asfour.tn.entites.Task;



public interface TaskRepository extends JpaRepository<Task, Long> {

}
