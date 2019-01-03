package nantes.asfour.tn.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import nantes.asfour.tn.entites.Task;


@RepositoryRestResource
public interface TaskRepository extends JpaRepository<Task, Long> {

}
