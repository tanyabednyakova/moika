package io.khasang.moika.entity;

import javax.persistence.*;

@Entity (name = "service_status")
public class ServiceStatus {
    @Id
    @Column(name = "id_status", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "code", unique = true, nullable = false)
    private String code;
    @Column(name = "name")
    private String name;

    public ServiceStatus() {
    }

    public ServiceStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public ServiceStatus(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ServiceStatus{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
