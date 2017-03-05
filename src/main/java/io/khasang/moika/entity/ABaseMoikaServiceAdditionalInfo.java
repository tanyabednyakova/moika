package io.khasang.moika.entity;

import javax.persistence.Column;
import java.math.BigDecimal;

public abstract class ABaseMoikaServiceAdditionalInfo extends BaseMoikaService implements MoikaService, MoikaServiceAdditinalInfo {
    ABaseMoikaServiceAdditionalInfo  moikaServiceAdditionalInfo;
    @Column(name = "cost")
    protected BigDecimal serviceCost = new BigDecimal("0.00");


    public abstract ABaseMoikaServiceAdditionalInfo getMoikaServiceAdditinalInfo();

    public BigDecimal getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(BigDecimal cost) {
        this.serviceCost = cost;
    }
}
