package io.khasang.moika.dao;

import io.khasang.moika.entity.Ordern;

import java.util.List;

public interface OrderDao {
    Ordern addOrder(Ordern ordern);
    Ordern updateOrder(Ordern ordern);
    void deleteOrder(Ordern ordern);
    Ordern getOrder(long id);
    List<Ordern> getAllOrder();
}
