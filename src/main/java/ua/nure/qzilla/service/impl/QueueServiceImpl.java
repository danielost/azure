package ua.nure.qzilla.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.qzilla.model.Queue;
import ua.nure.qzilla.repository.QueueRepository;
import ua.nure.qzilla.service.QueueService;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class QueueServiceImpl implements QueueService {

    @Autowired
    private QueueRepository queueRepository;

    @Override
    public List<Queue> getAll() {
        return queueRepository.findAll();
    }

    @Override
    public Queue create(Queue queue) {
        LocalDate currentDate = LocalDate.now();
        if (currentDate.isAfter(queue.getEventDate())) {
            throw new IllegalArgumentException("Event date must be later than the current date");
        }
        if (Objects.equals(queue.getName(), "")) {
            throw new IllegalArgumentException("Name can't be empty");
        }
        if (queueRepository.findByName(queue.getName()) != null) {
            throw new IllegalArgumentException("Name is taken");
        }
        queue.setDateCreated(currentDate);
        queue.setPeopleAmount(0);
        return queueRepository.save(queue);
    }

    @Override
    public void enroll(long id) {
        Optional<Queue> optionalQueue = queueRepository.findById(id);
        if (!optionalQueue.isPresent()) {
            throw new NullPointerException("No such queue");
        }

        Queue queue = optionalQueue.get();
        queue.setPeopleAmount(queue.getPeopleAmount() + 1);
        queueRepository.save(queue);
    }
}
