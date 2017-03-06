package io.khasang.moika.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "all_services")
@PrimaryKeyJoinColumn(name = "id_service")
public class MoikaAllService extends ABaseMoikaServiceAdditionalInfo {

    @Column(name = "add_info")
    private String AddInfo;

    public MoikaAllService() {
    }

    @Override
    public MoikaAllService getMoikaServiceAdditinalInfo() {
        return this;
    }
}
