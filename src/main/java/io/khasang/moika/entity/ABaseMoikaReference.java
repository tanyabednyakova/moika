package io.khasang.moika.entity;

import javax.persistence.*;

@Entity
public abstract class ABaseMoikaReference extends ABaseMoikaEntity {

    @Id
    @Column(name = "id_type", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column(name = "type_code", unique = true, nullable = false)
    public String typeCode;
    @Column(name = "type_name")
    public String typeName;
    @Column(name = "descr")
    public String description;

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
        return  this.getClass().getName()+ "{" +
                "id=" + id +
                ", typeCode='" + typeCode + '\'' +
                ", typeName='" + typeName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
