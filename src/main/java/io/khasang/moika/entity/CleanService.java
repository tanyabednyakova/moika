package io.khasang.moika.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name= "clean_Service")
public class CleanService implements MoikaServiceAdditinalInfo {

    @Id
    @Column(name = "id_service")
    private int id;

    @OneToOne (cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_service", foreignKey = @ForeignKey(name = "fk_id_service"))
    private MoikaServiceBase moikaServiceBase;

    @Column(name = "id_dirt_type")
    private Short idDirtType;
    @ManyToOne
    @JoinColumn(name = "id_dirt_type", foreignKey = @ForeignKey(name = "fk_dirt_type"), insertable=false, updatable=false )
    private CarType dirtTypeEntity;

    @Column(name = "cost")
    private BigDecimal cost = BigDecimal.valueOf(0.00D);

    public CleanService(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Short getIdDirtType() {
        return idDirtType;
    }

    public void setIdDirtType(Short idDirtType) {
        this.idDirtType = idDirtType;
    }

    public CarType getDirtTypeEntity() {
        return dirtTypeEntity;
    }


    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public CleanService getMoikaServiceAdditinalInfo(int idService) {
        return this;
    }



}
