package io.khasang.moika.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double price;
    private int amount;

    @OneToMany(mappedBy = "product")
    private Set<Bucket> buckets;

    public Product() {

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Set<Bucket> getBuckets() {
        return buckets;
    }

    public void setBuckets(Set<Bucket> buckets) {
        this.buckets = buckets;
    }
}
