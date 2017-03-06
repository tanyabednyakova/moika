package io.khasang.moika.entity;

import javax.persistence.Entity;

@Entity(name = "dirt_types")
public class DirtType extends ABaseMoikaTypeReference {

    public DirtType() {
    }

    public DirtType(String typeCode) {
        this.typeCode = typeCode;
    }

    public DirtType(String typeCode, String typeName) {
        this.typeCode = typeCode;
        this.typeName = typeName;
    }


}
