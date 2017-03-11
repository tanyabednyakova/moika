package io.khasang.moika.service.impl;

import io.khasang.moika.entity.Queue;

import java.util.List;

public interface QueueService {
    void addQueue(Queue company);
    Queue getQueueById(long id);
    void updateQueue(Queue queue);
    void deletуQueue(long id);
    List<Queue> getQueueList();
}
