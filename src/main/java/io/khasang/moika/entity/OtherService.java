package io.khasang.moika.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "other_services")
@PrimaryKeyJoinColumn(name = "id_service")
public class OtherService extends ABaseMoikaServiceAdditionalInfo {

    @Id
    @Column(name = "id_service")
    private int id;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_service", foreignKey = @ForeignKey(name = "fk_id_service"))
    private MoikaAllService moikaServiceBase;

    @Column(name = "add_info")
    private String AddInfop;


    public OtherService() {
    }

    @Override
    public OtherService getMoikaServiceAdditinalInfo() {
        return this;
    }

}
