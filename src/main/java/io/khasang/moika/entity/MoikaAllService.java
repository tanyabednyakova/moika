package io.khasang.moika.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name= "all_services")
@PrimaryKeyJoinColumn(name="id_service")
public class MoikaAllService extends ABaseMoikaServiceAdditionalInfo {

    @Column(name = "add_info")
    private String AddInfo;

    @Column(name = "cost")
    private BigDecimal cost;


    public MoikaAllService(){};

    @Override
    public BigDecimal getServiceCost() {
        return cost;
    }

    @Override
    public void setServiceCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public MoikaAllService getMoikaServiceAdditinalInfo() {
        return this;
    }
}
