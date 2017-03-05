package io.khasang.moika.dao;

import io.khasang.moika.entity.OrdersDetail;
import io.khasang.moika.entity.Work;

import java.math.BigDecimal;
import java.util.List;

public interface OrdersDetailDao {
    void addOrdersDetail(OrdersDetail ordersDetail);
    void updateOrdersDetail(OrdersDetail ordersDetail);
    void deleteOrdersDetailk(OrdersDetail ordersDetail);
    OrdersDetail getOrdersDetail(long id);
    OrdersDetail fillOrdersDetail(OrdersDetail ordersDetail, Work work, BigDecimal quantity);
    List<OrdersDetail> getOrdersDetailForOrder(long idOrder);
    List<OrdersDetail> getAllOrdersDetail();
}
