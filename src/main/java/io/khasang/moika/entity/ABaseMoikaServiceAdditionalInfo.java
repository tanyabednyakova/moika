package io.khasang.moika.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

@MappedSuperclass
public abstract class ABaseMoikaServiceAdditionalInfo<T extends ABaseMoikaServiceAdditionalInfo> extends BaseMoikaService implements MoikaService, MoikaServiceAdditinalInfo {

    @Column(name = "cost")
    protected BigDecimal serviceCost = new BigDecimal("0.00");


    public abstract T getMoikaServiceAdditinalInfo();

    public BigDecimal getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(BigDecimal cost) {
        this.serviceCost = cost;
    }
}
