package io.khasang.moika.entity;

import javax.persistence.*;

@Entity(name = "service_types")
public class ServiceType extends ABaseMoikaTypeReference {

    public ServiceType() {
    }

    public ServiceType(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
