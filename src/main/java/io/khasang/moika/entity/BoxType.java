package io.khasang.moika.entity;

import javax.persistence.*;

@Entity(name = "box_types")
public class BoxType extends ABaseMoikaTypeReference {

    public BoxType() {
    }

    public BoxType(String typeCode) {
        this.typeCode = typeCode;
    }

    public BoxType(String typeCode, String typeName) {
        this.typeCode = typeCode;
        this.typeName = typeName;
    }


}
