package io.khasang.moika.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "clients")
public class Client {
    @Id
    private long id;
    private String name;
    private String lastname;
    private String phone;
    @Column(name = "car_id")
    private long carId;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    public Client() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }
}
