package io.khasang.moika.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name= "chem_clean_services")
@PrimaryKeyJoinColumn(name="id_service")
public class ChemCleanService extends ABaseMoikaServiceAdditionalInfo   {

    @Column(name = "id_dirt_type")
    private int idDirtType;
    @ManyToOne
    @JoinColumn(name = "id_dirt_type", foreignKey = @ForeignKey(name = "fk_dirt_type"), insertable=false, updatable=false )
    private DirtType dirtTypeEntity;

    @Column(name = "id_material")
    private int idMaterial;
    @ManyToOne
    @JoinColumn(name = "id_material", foreignKey = @ForeignKey(name = "fk_salon_materials"), insertable=false, updatable=false )
    private SalonMaterial salonMaterial;

    @Column(name = "cost")
    private BigDecimal cost ;

    public ChemCleanService(){}

    public int getidDirtType() {
        return idDirtType;
    }

    public void setIdDirtType(Short idDirtType) {
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

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Short idMaterial) {
        this.idMaterial = idMaterial;
    }

    public SalonMaterial getSalonMaterial() {
        return salonMaterial;
    }

    public void setSalonMaterial(SalonMaterial salonMaterial) {
        this.salonMaterial = salonMaterial;
    }

    @Override
    public ChemCleanService getMoikaServiceAdditinalInfo() {
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
