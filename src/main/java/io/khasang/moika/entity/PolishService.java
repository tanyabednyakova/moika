package io.khasang.moika.entity;

import javax.persistence.*;

@Entity(name = "polish_services")
@PrimaryKeyJoinColumn(name = "id_service")
public class PolishService extends ABaseMoikaServiceAdditionalInfo {


    public String getAddInfop() {
        return AddInfop;
    }

    public void setAddInfop(String addInfop) {
        AddInfop = addInfop;
    }

    @Column(name = "add_info")
    private String AddInfop;


    public PolishService() {
    }



}
