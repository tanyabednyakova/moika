package io.khasang.moika.entity;

import javax.persistence.*;

@MappedSuperclass
@PrimaryKeyJoinColumn(name = "id_type")
public abstract class ABaseMoikaTypeReference {

    @Id
    @Column(name = "id_type", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @Column(name = "type_code", unique = true, nullable = false)
    protected String typeCode;
    @Column(name = "type_name")
    protected String typeName;
    @Column(name = "descr")
    protected String description;

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
