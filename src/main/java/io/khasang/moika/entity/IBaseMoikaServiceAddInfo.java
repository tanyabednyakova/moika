package io.khasang.moika.entity;

import org.postgresql.util.PGInterval;

import java.math.BigDecimal;

/**
 * Интерфейс для получения/обновления данных по конкретному сервису
 */
public interface IBaseMoikaServiceAddInfo {
    BigDecimal getServiceCost();

    void setServiceCost(BigDecimal cost);

    PGInterval getServiceDuration();

    int getDurationMinutes();

    void setServiceDuration(PGInterval serviceDuration);

    void setServiceDuration(int serviceDurationInMinutes);

    String getAdditionalServiceInfo();

    void setAdditionalServiceInfo(String additionalServiceInfo);
}
