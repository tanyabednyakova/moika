package io.khasang.moika.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "clean_Services")
@PrimaryKeyJoinColumn(name = "id_service")
public class CleanService extends ABaseMoikaServiceAdditionalInfo {

    @Column(name = "id_dirt_type")
    private int idDirtType;
    @ManyToOne
    @JoinColumn(name = "id_dirt_type", foreignKey = @ForeignKey(name = "fk_dirt_type"), insertable = false, updatable = false)
    private DirtType dirtTypeEntity;


    public CleanService() {
    }

    public int getidDirtType() {
        return idDirtType;
    }

    public void setIdDirtType(int idDirtType) {
        this.idDirtType = idDirtType;
    }

    public String getDirtTypeCode() {
        return this.dirtTypeEntity.getTypeCode();
    }


    public DirtType getDirtTypeEntity() {
        return dirtTypeEntity;
    }

    public void setDirtTypeEntity(DirtType dirtTypeEntity) {
        this.dirtTypeEntity = dirtTypeEntity;
    }


}
