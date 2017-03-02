package io.khasang.moika.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name= "Wash_Service")
public class WashService implements MoikaServiceAdditinalInfo {

    @Id
    @Column(name = "id_service")
    private int id;

    @OneToOne (cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_service", foreignKey = @ForeignKey(name = "fk_id_service"))
    private MoikaServiceBase moikaServiceBase;

    @Column(name = "id_car_type")
    private Short idCarType;
    @ManyToOne
    @JoinColumn(name = "id_car_type", foreignKey = @ForeignKey(name = "fk_car_type"), insertable=false, updatable=false )
    private CarType carTypeEntity;

    @Column(name = "cost")
    private BigDecimal cost = BigDecimal.valueOf(0.00D);

    public WashService(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Short getIdCarType() {
        return idCarType;
    }

    public void setIdCarType(Short idCarType) {
        this.idCarType = idCarType;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public WashService getMoikaServiceAdditinalInfo(int idService) {
        return this;
    }

}
