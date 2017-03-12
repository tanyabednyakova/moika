package io.khasang.moika.entity;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.postgresql.util.PGInterval;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.math.BigDecimal;

/**
 * Абстрактный класс расширения для потомков моечных сервисов
 *
 */
@MappedSuperclass
@TypeDef(name="interval", typeClass = PGInterval.class)
public abstract class ABaseMoikaServiceAdditionalInfo extends ABaseMoikaEntity implements IBaseMoikaServiceAddInfo {


    @Column(name = "cost")
    protected BigDecimal serviceCost = new BigDecimal("0.00");

    @Column(name = "duration")
    @Type(type = "interval")
    protected PGInterval serviceDuration;

    @Transient
    protected String AdditionalServiceInfo;


    public BigDecimal getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(BigDecimal cost) {
        this.serviceCost = cost;
    }

    public PGInterval getServiceDuration() {
        return serviceDuration;
    }

    public int getDurationMinutes(){
        return serviceDuration.getMinutes();
    }

    public void setServiceDuration(PGInterval serviceDuration) {
        this.serviceDuration = serviceDuration;
    }

    public void setServiceDuration(int serviceDurationInMinutes) {
        this.serviceDuration.setMinutes(serviceDurationInMinutes);
    }

    public String getAdditionalServiceInfo() {
        return AdditionalServiceInfo;
    }

    public void setAdditionalServiceInfo(String additionalServiceInfo) {
        AdditionalServiceInfo = additionalServiceInfo;
    }

}
