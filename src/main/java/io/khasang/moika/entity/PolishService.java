package io.khasang.moika.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name= "polish_Service")
public class PolishService implements MoikaServiceAdditinalInfo {

    @Id
    @Column(name = "id_service")
    private int id;

    @OneToOne (cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_service", foreignKey = @ForeignKey(name = "fk_id_service"))
    private MoikaServiceBase moikaServiceBase;

    @Column(name = "add_info")
    private String AddInfop;

    @Column(name = "cost")
    private BigDecimal cost = BigDecimal.valueOf(0.00D);

    PolishService(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddInfop() {
        return AddInfop;
    }

    public void setAddInfop(String addInfop) {
        AddInfop = addInfop;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public PolishService getMoikaServiceAdditinalInfo(int idService) {
        return this;
    }



}
