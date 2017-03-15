package io.khasang.moika.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "wash_facilities")
public class WashFacility  extends ABaseMoikaEntity  {

    @Id
    @Column(name = "id_fclt", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "id_net")
    private int idNet;
    @Column(name = "id_manager")
    private int  idManager;
    @Column(name = "name")
    private String  name ;
    @Column(name = "id_addr")
    private int  idAddr;
    @Column(name = "descr")
    private String  description;
    @OneToMany (cascade = CascadeType.ALL, orphanRemoval = true) //(mappedBy="washFacility")
    @JoinColumn(name = "id_fclt", referencedColumnName = "id_fclt", foreignKey = @ForeignKey(name = "fk_box_facility"))
    private List<WashBox> washBoxes  = new ArrayList<>();

    public WashFacility() {
    }

    public int getId() {
        return id;
    }

    public int getIdNet() {
        return idNet;
    }

    public void setIdNet(int idNet) {
        this.idNet = idNet;
    }

    public int getIdManager() {
        return idManager;
    }

    public void setIdManager(int idManager) {
        this.idManager = idManager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdAddr() {
        return idAddr;
    }

    public void setIdAddr(int idAddr) {
        this.idAddr = idAddr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<WashBox> getWashBoxes() {
        return washBoxes;
    }

    public void setWashBoxes(List<WashBox> washBowes) {
        this.washBoxes = washBowes;
    }
}
