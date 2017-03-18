package io.khasang.moika.entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;

@Entity(name = "wash_boxes")
public class WashBox  extends ABaseMoikaEntity {

    @Id
    @Column(name = "id_box", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_fclt", insertable=false, updatable=false)
    private int idFacility;
    @ManyToOne//()
    @JoinColumn(name = "id_fclt", insertable=false, updatable=false )
    private WashFacility washFacility;

    @Column(name = "id_type", insertable=false, updatable=false)
    private int idType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn( name = "id_type")//, foreignKey = @ForeignKey(name = "fk_box_type"), insertable=false, updatable=false )
    private BoxType boxTypeEntity;

    @Column(name = "name", unique = true)
    private String boxName;

    @Column(name = "descr")
    private String description;

    @Column(name = "status", insertable=false, updatable=false)
    private Short boxStatus;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status")//, insertable=false, updatable=false )
    private BoxStatus boxStatusEntity;


    public WashBox() {
    }

    public WashBox(int idFacility, String name, int idBoxType ) {
        this.idFacility = idFacility;
        this.boxName = name;
        this.idType = idBoxType;
    }

    public int getId() {
        return id;
    }

    public int getIdFacility() {
        return idFacility;
    }

    public void setIdFacility(int idFacility) {
        this.idFacility = idFacility;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdtype(int idType) {
        this.idType = idType;
    }

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBoxStatus() {
        return boxStatus;
    }

    public void setBoxStatus(Short boxStatus) {
        this.boxStatus = boxStatus;
    }

    public BoxType getBoxTypeEntity() {
        return boxTypeEntity;
    }

    public BoxStatus getBoxStatusEntity() {
        return boxStatusEntity;
    }

    public WashFacility getWashFacility() {
        return washFacility;
    }

    public void setBoxTypeEntity(BoxType boxTypeEntity) {
        this.boxTypeEntity = boxTypeEntity;
    }

    public void setBoxStatusEntity(BoxStatus boxStatusEntity) {
        this.boxStatusEntity = boxStatusEntity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWashFacility(WashFacility washFacility) {
        this.washFacility = washFacility;
    }

    /*@Override
    public String toString() {
        return "WashBox{" +
                "id=" + id +
                ", idFacility=" + idFacility +
                ", boxName='" + boxName + '\'' +
                ", boxTypeEntity=" + boxTypeEntity.toString() +
                ", boxStatusEntity=" + boxStatusEntity.toString() + '\'' +
                ", description='" + description  +
                '}';
    }*/
}
