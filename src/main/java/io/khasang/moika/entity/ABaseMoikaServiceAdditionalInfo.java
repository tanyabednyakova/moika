package io.khasang.moika.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@MappedSuperclass
public abstract class ABaseMoikaServiceAdditionalInfo extends BaseMoikaService  {

    @Column(name = "cost")
    protected BigDecimal serviceCost = new BigDecimal("0.00");


    public BigDecimal getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(BigDecimal cost) {
        this.serviceCost = cost;
    }
}
