package io.khasang.moika.entity;

import javax.persistence.*;

@MappedSuperclass
@PrimaryKeyJoinColumn(name = "id_type")
public abstract class ABaseMoikaStatusReference extends ABaseMoikaEntity {

    @Id
    @Column(name = "id_status", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @Column(name = "code", unique = true, nullable = false)
    protected String statusCode;
    @Column(name = "name")
    protected String statusName;


    public int getId() {
        return id;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return  this.getClass().getName()+ "{" +
                "id=" + id +
                ", typeCode='" + statusCode + '\'' +
                ", typeName='" + statusName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ABaseMoikaStatusReference)) return false;

        ABaseMoikaStatusReference that = (ABaseMoikaStatusReference) o;

        if (getId() != that.getId()) return false;
        return getStatusCode().equals(that.getStatusCode());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getStatusCode().hashCode();
        result = 31 * result + (getStatusName() != null ? getStatusName().hashCode() : 0);
        return result;
    }
}
