package io.khasang.moika.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name= "wash_service")
@PrimaryKeyJoinColumn(name="id_service")
public class WashService extends ABaseMoikaServiceAdditionalInfo {

    @Column(name = "id_car_type")
    private Short idCarType;
    @ManyToOne
    @JoinColumn(name = "id_car_type", foreignKey = @ForeignKey(name = "fk_car_type"), insertable=false, updatable=false )
    private CarType carTypeEntity;

    @Column(name = "cost")
    private BigDecimal cost ;

    public WashService(){}

    public Short getIdCarType() {
        return idCarType;
    }

    public void setIdCarType(Short idCarType) {
        this.idCarType = idCarType;
    }

    public String getCarTypeCode() {
        return this.carTypeEntity.getTypeCode();
    }

    public void setCarTypeByCode(String code) {
        CarType carType = new CarType();
        carType.setTypeCode(code);
        this.carTypeEntity = carType;
    }

    @Override
    public BigDecimal getServiceCost() {
        return cost;
    }

    @Override
    public void setServiceCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public WashService getMoikaServiceAdditinalInfo() {
        return this;
    }
}
