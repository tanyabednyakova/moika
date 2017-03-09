package io.khasang.moika.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "all_services")
@PrimaryKeyJoinColumn(name = "id_service")
public class AllService extends ABaseMoikaServiceAdditionalInfo {

    @Column(name = "add_info")
    private String AddInfo;


    public AllService() {
    }

    public String getAddInfo() {
        return AddInfo;
    }

    public void setAddInfo(String addInfo) {
        AddInfo = addInfo;
    }
}
