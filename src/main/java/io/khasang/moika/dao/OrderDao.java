package io.khasang.moika.dao;

import io.khasang.moika.entity.Order;

import java.util.List;

public interface OrderDao {
    Order addOrder(Order order);
    Order updateOrder(Order order);
    void deleteOrder(Order order);
    Order getOrder(long id);
    List<Order> getAllOrder();
}
