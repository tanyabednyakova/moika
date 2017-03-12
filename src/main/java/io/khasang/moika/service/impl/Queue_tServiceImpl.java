package io.khasang.moika.service.impl;

import io.khasang.moika.dao.QueueDAO;
import io.khasang.moika.entity.Queue_t;
import io.khasang.moika.service.Queue_tServiсe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("QueueServiceImpl")
@Transactional
public class Queue_tServiceImpl implements Queue_tServiсe{
    @Autowired
    QueueDAO queueDAO;

    public Queue_tServiceImpl() {
    }

    @Override
    public void createQueue(Queue_t queue_t) {

    }

    @Override
    public void updateQueue(Queue_t queue) {
        queueDAO.updateQueue(queue);
    }

    @Override
    public void deleteQueue(Queue_t queue_t) {

    }

    @Override
    public Queue_t getQueue_id(int id) {
        return null;
    }

    @Override
    public List<Queue_t> getAllQueue() {
        return queueDAO.getAllQueue();
    }
}
