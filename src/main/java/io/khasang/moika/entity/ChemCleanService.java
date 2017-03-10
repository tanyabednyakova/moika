package io.khasang.moika.entity;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity(name= "v_chem_clean_services")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id_service")),
        @AttributeOverride(name = "name", column = @Column(name = "name")),
        @AttributeOverride(name = "idFacility", column = @Column(name = "id_fclt")),
        @AttributeOverride(name = "idType", column = @Column(name = "id_type")),
        @AttributeOverride(name = "idStatus", column = @Column(name = "id_status")),
        @AttributeOverride(name = "description", column = @Column(name = "description"))
})
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

    public DirtType getDirtTypeEntity() {
        return dirtTypeEntity;
    }

    public void setDirtTypeEntity(DirtType dirtTypeEntity) {
        this.dirtTypeEntity = dirtTypeEntity;
    }
}
