package io.khasang.moika.entity;

import javax.persistence.*;

@Entity(name = "service_types")
public class ServiceType {

    @Id
    @Column(name = "id_type", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "type_code", unique = true, nullable = false)
    private String typeCode;
    @Column(name = "type_name")
    private String typeName;
    @Column(name = "description")
    private String description;

    public ServiceType() {
    }

    public ServiceType(String typeCode) {
        this.typeCode = typeCode;
    }

    public ServiceType(String typeCode, String typeName) {
        this.typeCode = typeCode;
        this.typeName = typeName;
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
        return "Service Type{" +
                "id=" + id +
                ", typeCode='" + typeCode + '\'' +
                ", typeName='" + typeName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
