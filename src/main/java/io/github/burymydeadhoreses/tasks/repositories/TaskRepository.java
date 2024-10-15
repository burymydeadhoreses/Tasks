package io.github.burymydeadhoreses.tasks.repositories;

import io.github.burymydeadhoreses.tasks.entities.Task;
import io.github.burymydeadhoreses.tasks.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query("SELECT t FROM Task t WHERE t.status = ?1")
    List<Task> findByStatus(Status status);
}
