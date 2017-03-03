package io.khasang.moika.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 15, unique = true,nullable = false)
    private String nunber;
    @Temporal(TemporalType.DATE)
    private Date registrationDate;
    @Temporal(TemporalType.DATE)
    private Date executiontionDate;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdersDetail> ordersDetails = new ArrayList<>();

//
//    id_fclt integer NOT NULL,
//    id_car integer NOT NULL,
//    id_client integer NOT NULL,
//    id_discount integer,
//    id_shedule integer NOT NULL,
//    add_info character varying(255) COLLATE pg_catalog."default",
//    start_sum numeric(10, 2),
//    discount_sum numeric(10, 2),
//    final_sum numeric(10, 2),
//    is_prepaid boolean DEFAULT false,
//    is_canceled boolean DEFAULT false,
//    id_work integer,
//    CONSTRAINT pk_id_order PRIMARY KEY (id_order)
}
