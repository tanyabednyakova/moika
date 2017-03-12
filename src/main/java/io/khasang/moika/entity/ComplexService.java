package io.khasang.moika.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "complex_services")
public class ComplexService extends ABaseMoikaServiceAdditionalInfo {

    @Id
    @Column(name = "id_service")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "add_info")
    private String addInfo;

    public ComplexService() {
    }

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }


}
