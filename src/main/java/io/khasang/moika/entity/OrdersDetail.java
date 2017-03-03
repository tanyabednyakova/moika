package io.khasang.moika.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class OrdersDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 15, scale =2, unique = true,nullable = false)
    private BigDecimal quantity;
    @Column(length = 15, scale =2, unique = true,nullable = false)
    private BigDecimal sum;
    @ManyToOne
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "ORDER_ID_FK"))
    private Order order;
}
