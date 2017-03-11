package io.khasang.moika.service.impl;

import io.khasang.moika.dao.QueueDAO;
import io.khasang.moika.entity.Queue;
import io.khasang.moika.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("QueueServiceImpl")
@Transactional
public class QueueServiceImpl implements QueueService{
    @Autowired
    QueueDAO queueDAO;

    public QueueServiceImpl() {
    }

    @Override
    public void addQueue(Queue queue) {
        queueDAO.addQueue(queue);
    }

    @Override
    public Queue getQueueById(long id) {
        return queueDAO.getQueueById(id);
    }

    @Override
    public void updateQueue(Queue queue) {
        queueDAO.updateQueue(queue);
    }

    @Override
    public void deletуQueue(Queue queue) {
        queueDAO.deleteQueue(queue);
    }

    @Override
    public List<Queue> getAllQueue() {
        return queueDAO.getAllQueue();
    }
}
