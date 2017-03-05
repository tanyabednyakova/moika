package io.khasang.moika.entity;

import javax.persistence.*;

@Entity (name = "box_status")
public class BoxStatus extends ABaseMoikaStatusReference{

    public BoxStatus() {
    }

    public BoxStatus(String code, String name) {
        this.statusCode = code;
        this.statusName = name;
    }

    public BoxStatus(String code) {
        this.statusCode = code;
    }

}
