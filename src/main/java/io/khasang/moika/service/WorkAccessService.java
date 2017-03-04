package io.khasang.moika.service;

import io.khasang.moika.entity.Work;

import java.util.List;

public interface WorkAccessService {
    void addWork(Work work);
    void updateWork(Work work);
    void deleteWork(Work work);
    Work getWork(long id);
    Work getWork(String name);
    List<Work> getAllWork();
}
