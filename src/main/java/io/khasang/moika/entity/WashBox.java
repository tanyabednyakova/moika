package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "wash_boxes")
public class WashBox  extends ABaseMoikaEntity {

    @Id
    @Column(name = "id_box")   // columnDefinition = "serial"
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

   // @Column(name = "id_fclt", insertable=false, updatable=false)
   // private int idFacility;

    @ManyToOne//(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_fclt",  foreignKey = @ForeignKey(name = "fk_box_facility"))
    //@JoinColumn(name = "id_fclt", insertable=false, updatable=false )
    @JsonIgnore
    private WashFacility washFacility;

  //  @Column(name = "id_type")
 //   private int idType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "id_type",   foreignKey = @ForeignKey(name = "fk_box_type"))
    private BoxType boxTypeEntity;

    @Column(name = "name", unique = true)
    private String boxName;

    @Column(name = "descr")
    private String description;

   // @Column(name = "status")
  //  private Short boxStatus;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_status", foreignKey = @ForeignKey(name = "fk_box_status"))
    private BoxStatus boxStatusEntity;


    public WashBox() {
    }

    public WashBox(int idFacility, String name, int idBoxType ) {
      //  this.idFacility = idFacility;
        this.boxName = name;
        this.boxTypeEntity = new BoxType("CAR");
    }

    public int getId() {
        return id;
    }

    public int getIdFacility() {
        return washFacility.getId();
    }
/*
    public void setIdFacility(int idFacility) {
        this.idFacility = idFacility;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdtype(int id_type) {
        this.idType = id_type;
    }

    public int getBoxStatus() {
        return boxStatus;
    }

    public void setBoxStatus(Short boxStatus) {
        this.boxStatus = boxStatus;
    }
    */

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


    public BoxType getBoxTypeEntity() {
        return boxTypeEntity;
    }

    public BoxStatus getBoxStatusEntity() {
        return boxStatusEntity;
    }

    public WashFacility getWashFacility() {
        return washFacility;
    }

    public void setWashFacility(WashFacility washFacility) {
       this.washFacility = washFacility;
    }

    public void setBoxTypeEntity(BoxType boxTypeEntity) {
        this.boxTypeEntity = boxTypeEntity;
    }

    public void setBoxStatusEntity(BoxStatus boxStatusEntity) {
        this.boxStatusEntity = boxStatusEntity;
    }

    @Override
    public String toString() {
        return "WashBox{" +
                "id=" + id +
                ", idFacility=" + washFacility.getId()+
                ", boxName='" + boxName + '\'' +
                ", boxTypeEntity=" + boxTypeEntity.toString() +
                ", boxStatusEntity=" + boxStatusEntity.toString() + '\'' +
                ", description='" + description  +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WashBox)) return false;

        WashBox washBox = (WashBox) o;

        if (getId() != washBox.getId()) return false;
        if (washFacility.getId() != washBox.getIdFacility()) return false;
        if (!getBoxName().equals(washBox.getBoxName())) return false;
        return getDescription() != null ? getDescription().equals(washBox.getDescription()) : washBox.getDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + washFacility.getId();
        result = 31 * result + getBoxName().hashCode();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }
}
