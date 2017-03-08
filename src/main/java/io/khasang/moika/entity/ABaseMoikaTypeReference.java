package io.khasang.moika.entity;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ABaseMoikaTypeReference {

    @Id
    @Column(name = "id_type", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @Column(name = "type_code", unique = true, nullable = false)
    protected String code;
    @Column(name = "type_name")
    protected String name;
    @Column(name = "descr")
    protected String description;

    public ABaseMoikaTypeReference() {
    }

    public ABaseMoikaTypeReference(String typeCode) {
        this.code = typeCode;
    }

    public ABaseMoikaTypeReference(String typeCode, String typeName) {
        this.code = typeCode;
        this.name = typeName;
    }

    public int getId() {
        return id;
    }

    public String getTypeCode() {
        return this.code;
    }

    public void setTypeCode(String typeCode) {
        this.code = typeCode;
    }

    public String getTypeName() {
        return this.name;
    }

    public void setTypeName(String typeName) {
        this.name = typeName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return  this.getClass().getName()+ "{" +
                "id=" + id +
                ", typeCode='" + code + '\'' +
                ", typeName='" + name + '\'' +
                ((description != null) ? ", description='" + description : "") + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ABaseMoikaTypeReference)) return false;

        ABaseMoikaTypeReference that = (ABaseMoikaTypeReference) o;

        if (getId() != that.getId()) return false;
        return getTypeCode().equals(that.getTypeCode());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getTypeCode().hashCode();
        return result;
    }
}
