package io.khasang.moika.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "polish_services")
@PrimaryKeyJoinColumn(name = "id_service")
public class PolishService extends ABaseMoikaServiceAdditionalInfo {


    @Column(name = "add_info")
    private String AddInfop;


    public PolishService() {
    }



}
