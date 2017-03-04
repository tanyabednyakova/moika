package io.khasang.moika.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name= "complex_service")
@PrimaryKeyJoinColumn(name="id_service")
public class ComplexService extends ABaseMoikaServiceAdditionalInfo {
    /*

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "comlex_service_services", joinColumns = {
            @JoinColumn(name = "id_service", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "id_complex",
                    nullable = false, updatable = false) })
    private List<> clients = new ArrayList<>();


    @OneToOne (cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_service", foreignKey = @ForeignKey(name = "fk_id_service"))
    private MoikaAllService moikaServiceBase;

*/

    @Column(name = "cost")
    private BigDecimal cost;

    public ComplexService(){}


    @Override
    public ComplexService getMoikaServiceAdditinalInfo() {
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
