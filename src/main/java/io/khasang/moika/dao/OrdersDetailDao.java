package io.khasang.moika.dao;

import io.khasang.moika.entity.OrdersDetail;

import java.util.List;

public interface OrdersDetailDao {
    void addOrdersDetail(OrdersDetail ordersDetail);
    void updatOrdersDetail(OrdersDetail ordersDetail);
    void deleteOrdersDetailk(OrdersDetail ordersDetail);
    OrdersDetail getOrdersDetail(long id);
    List<OrdersDetail> getOrdersDetailForOrder(long idOrder);
    List<OrdersDetail> getAllOrdersDetail();
}
