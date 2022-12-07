package ua.nure.qzilla.service;

import ua.nure.qzilla.model.Queue;

import java.util.List;

public interface QueueService {
    List<Queue> getAll();

    Queue create(Queue queue);

    void enroll(long id);
}
