package io.khasang.moika.entity;

import javax.persistence.*;

@Entity(name = "car_types")
public class CarType {
    @Id
    @Column(name = "id_type", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "type_code", unique = true, nullable = false)
    private String typeCode;
    @Column(name = "type_name")
    private String typeName;
    @Column(name = "descr")
    private String description;

    public CarType() {
    }

    public int getId() {
        return id;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CarType{" +
                "id=" + id +
                ", typeCode='" + typeCode + '\'' +
                ", typeName='" + typeName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}


