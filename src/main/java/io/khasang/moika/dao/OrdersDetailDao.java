package io.khasang.moika.dao;

import io.khasang.moika.entity.OrdersDetail;
import io.khasang.moika.entity.Work;

import java.util.List;

public interface OrdersDetailDao {
    void addOrdersDetail(OrdersDetail ordersDetail);
    void updatOrdersDetail(OrdersDetail ordersDetail);
    void deleteOrdersDetailk(OrdersDetail ordersDetail);
    Work getOrdersDetail(long id);
    Work getOrdersDetail(String name);
    List<OrdersDetail> getAllWork();
}
