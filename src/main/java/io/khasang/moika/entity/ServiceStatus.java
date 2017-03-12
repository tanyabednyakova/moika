package io.khasang.moika.entity;

import javax.persistence.*;

@Entity (name = "service_status")
public class ServiceStatus extends ABaseMoikaStatusReference{


    public ServiceStatus() {
    }

    public ServiceStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }

}
