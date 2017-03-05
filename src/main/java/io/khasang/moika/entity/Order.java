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
    private String number;
    @Temporal(TemporalType.DATE)
    private Date registrationDate;
    @Temporal(TemporalType.DATE)
    private Date executiontionDate;
    private boolean is_prepaid;
    private boolean is_made;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdersDetail> ordersDetails = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "car_id", foreignKey = @ForeignKey(name = "CAR_ID_FK"))
    private Car car;

    @ManyToOne
    @JoinColumn(name = "client_id", foreignKey = @ForeignKey(name = "CLIENT_ID_FK"))
    private Client client;
// TODO: 03.03.2017   id_использование бокса

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String nunber) {
        this.number = nunber;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getExecutiontionDate() {
        return executiontionDate;
    }

    public void setExecutiontionDate(Date executiontionDate) {
        this.executiontionDate = executiontionDate;
    }

    public boolean is_prepaid() {
        return is_prepaid;
    }

    public void setIs_prepaid(boolean is_prepaid) {
        this.is_prepaid = is_prepaid;
    }

    public boolean is_made() {
        return is_made;
    }

    public void setIs_made(boolean is_made) {
        this.is_made = is_made;
    }

    public List<OrdersDetail> getOrdersDetails() {
        return ordersDetails;
    }

    public void setOrdersDetails(List<OrdersDetail> ordersDetails) {
        this.ordersDetails = ordersDetails;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
