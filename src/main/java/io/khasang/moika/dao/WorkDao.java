package io.khasang.moika.dao;

import io.khasang.moika.entity.Work;

import java.util.List;

public interface WorkDao {
    Work addWork(Work work);
    Work updateWork(Work work);
    void deleteWork(Work work);
    Work getWork(long id);
    Work getWork(String name);
    List<Work> getAllWork();
}

