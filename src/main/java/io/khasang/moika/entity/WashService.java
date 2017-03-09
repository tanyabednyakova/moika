package io.khasang.moika.entity;

import javax.persistence.*;

@Entity(name = "wash_services")
@PrimaryKeyJoinColumn(name = "id_service")
public class WashService extends ABaseMoikaServiceAdditionalInfo {

    @Column(name = "id_type_car")
    private int idCarType;

    @ManyToOne
    @JoinColumn(name = "id_type_car", foreignKey = @ForeignKey(name = "fk_car_type"), insertable = false, updatable = false)
    private CarType carTypeEntity;

    public WashService() {
    }

    public int getIdCarType() {
        return idCarType;
    }

    public void setIdCarType(int idCarType) {
        this.idCarType = idCarType;
    }

    public CarType getCarTypeEntity() {
        return carTypeEntity;
    }

    public void setCarTypeEntity(CarType carTypeEntity) {
        this.carTypeEntity = carTypeEntity;
    }

}
