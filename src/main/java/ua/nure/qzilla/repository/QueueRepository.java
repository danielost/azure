package ua.nure.qzilla.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.qzilla.model.Queue;

@Repository
public interface QueueRepository extends JpaRepository<Queue, Long> {
    Queue findByName(String name);
}
