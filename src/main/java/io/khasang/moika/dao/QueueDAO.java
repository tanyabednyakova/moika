package io.khasang.moika.dao;

import io.khasang.moika.entity.Queue;

import java.util.List;

public interface QueueDAO {
    void addQueue(Queue queue);
    void updateQueue(Queue queue);
    void deleteQueue(Queue queue);
    List<Queue> getAllQueue();
    Queue getQueueById(long id);

}
