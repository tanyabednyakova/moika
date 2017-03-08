package io.khasang.moika.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 80, unique = true,nullable = false)
    private String name;
    @Column(length = 15, scale =2, unique = true,nullable = false)
    private BigDecimal price;
/*
время в минутах для проведения работ в боксе
*/

//    private int timeInBox;
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<OrdersDetail> ordersDetails = new ArrayList<>();

    public Work() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

//    public int getTimeInBox() {
//        return timeInBox;
//    }
//
//    public void setTimeInBox(int timeInBox) {
//        this.timeInBox = timeInBox;
//    }
//
//    public List<OrdersDetail> getOrdersDetails() {
//        return ordersDetails;
//    }
//
//    public void setOrdersDetails(List<OrdersDetail> ordersDetails) {
//        this.ordersDetails = ordersDetails;
//    }
//    @Override
//    public String toString(){
//        System.out.format("id = %d name= %s price = %f timeInBox =%d",
//                id,name,price,timeInBox);
//        return null;
//    }

}
