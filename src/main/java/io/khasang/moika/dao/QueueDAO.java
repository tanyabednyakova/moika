package io.khasang.moika.dao;

import io.khasang.moika.entity.Queue_t;

import java.util.List;

public interface QueueDAO {
    void createQueue(Queue_t queue_t);
    void updateQueue(Queue_t queue_t);
    void deleteQueue(Queue_t queue_t);
    Queue_t getQueue_id(int id);
    List<Queue_t> getAllQueue();
}
