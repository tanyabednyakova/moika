package io.khasang.moika.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Абстрактный класс расширения для потомков моечных сервисов
 *
 */
@Entity
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id_service")),
        @AttributeOverride(name = "name", column = @Column(name = "name")),
        @AttributeOverride(name = "idFacility", column = @Column(name = "id_fclt")),
        @AttributeOverride(name = "idType", column = @Column(name = "id_type")),
        @AttributeOverride(name = "idStatus", column = @Column(name = "id_status")),
        @AttributeOverride(name = "description", column = @Column(name = "description"))
})
public abstract class ABaseMoikaServiceAdditionalInfo extends BaseMoikaService  {

    @Column(name = "cost")
    protected BigDecimal serviceCost = new BigDecimal("0.00");


    public BigDecimal getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(BigDecimal cost) {
        this.serviceCost = cost;
    }

    @Override
    public String toString() {
        return "Moiks service of " +this.getClass().getName()+"+ {" +
                "id=" + super.getId() +
                ", idFacility=" + super.getIdFacility() +
                ", washFacility=" + super.getWashFacility().getName() +
                ", idType=" + super.getServiceType() +
                ", serviceTypeEntity=" + super.getServiceTypeEntity().getCode() +
                ", idStatus=" + super.getIdStatus() +
                ", serviceStatusEntity=" + super.getServiceStatusEntity().getStatusName() +
                ", serviceName='" + super.getName() + '\'' +
                ", cost=" + serviceCost.toString()+
                ((super.getDescription() != null) ? ", description='" + super.getDescription()  : "") + '\'' +
                '}';
    }
}
