package io.khasang.moika.entity;

import javax.persistence.*;

@Entity(name = "other_services")
public class OtherService extends ABaseMoikaServiceAdditionalInfo {

    @Column(name = "add_info")
    private String AddInfop;


    public OtherService() {
    }

    public String getAddInfop() {
        return AddInfop;
    }

    public void setAddInfop(String addInfop) {
        AddInfop = addInfop;
    }
}
