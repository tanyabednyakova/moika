package io.khasang.moika.entity;

import javax.persistence.*;

@Entity (name = "box_status")
public class BoxStatus {
    @Id
    @Column(name = "id_status", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "code", unique = true, nullable = false)
    private String code;
    @Column(name = "name")
    private String name;

    public BoxStatus() {
    }

    public BoxStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public BoxStatus(String code) {
        this.code = code;
    }

    public long getId() {
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
        return "BoxStatus{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
