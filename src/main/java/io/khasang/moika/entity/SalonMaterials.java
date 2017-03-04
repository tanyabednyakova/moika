package io.khasang.moika.entity;

import javax.persistence.Entity;

@Entity(name = "salon_materials")
public class SalonMaterials extends ABaseMoikaReference {

    public SalonMaterials() {
    }

    public SalonMaterials(String typeCode) {
        this.typeCode = typeCode;
    }

    public SalonMaterials(String typeCode, String typeName) {
        this.typeCode = typeCode;
        this.typeName = typeName;
    }


}
