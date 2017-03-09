package io.khasang.moika.entity;

import javax.persistence.*;

@Entity(name = "car_types")
public class CarType extends ABaseMoikaTypeReference {

    public CarType() {
    }

    public CarType(String code, String name) {
        this.code = code;
        this.name = name;
    }
}


