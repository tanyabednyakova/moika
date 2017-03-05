package io.khasang.moika.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name= "clean_Services")
@PrimaryKeyJoinColumn(name="id_service")
public class CleanService extends  ABaseMoikaServiceAdditionalInfo  {

    @Column(name = "id_dirt_type")
    private int idDirtType;
    @ManyToOne
    @JoinColumn(name = "id_dirt_type", foreignKey = @ForeignKey(name = "fk_dirt_type"), insertable=false, updatable=false )
    private DirtType dirtTypeEntity;

    @Column(name = "cost")
    private BigDecimal cost ;

    public CleanService(){}

    public int getidDirtType() {
        return idDirtType;
    }

    public void setIdDirtType(int idDirtType) {
        this.idDirtType = idDirtType;
    }

    public String getDirtTypeCode() {
        return this.dirtTypeEntity.getTypeCode();
    }

    public void setDirtTypeByCode(String code) {
        DirtType dt = new DirtType();
        dt.setTypeCode(code);
        this.dirtTypeEntity = dt;
    }

    public DirtType getDirtTypeEntity() {
        return dirtTypeEntity;
    }

    public void setDirtTypeEntity(DirtType dirtTypeEntity) {
        this.dirtTypeEntity = dirtTypeEntity;
    }

    @Override
    public CleanService getMoikaServiceAdditinalInfo() {
        return this;
    }

    @Override
    public BigDecimal getServiceCost() {
        return cost;
    }

    @Override
    public void setServiceCost(BigDecimal cost) {
        this.cost = cost;
    }

}
