package io.khasang.moika.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "all_services")
@PrimaryKeyJoinColumn(name = "id_service")
public class AllService extends ABaseMoikaServiceAdditionalInfo {

    @Column(name = "add_info")
    private String AddInfop;


    public AllService() {
    }

    public String getAddInfop() {
        return AddInfop;
    }

    public void setAddInfop(String addInfop) {
        AddInfop = addInfop;
    }
}
