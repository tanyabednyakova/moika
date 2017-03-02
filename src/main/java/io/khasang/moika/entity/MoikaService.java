package io.khasang.moika.entity;

import java.math.BigDecimal;

public interface MoikaService {
    MoikaService createMoikaService(); //
    int getMoikaServiceId();
    String getMoikaServiceType();
    void setMoikaServiceType(int type);
    void setMoikaServiceType(String code);
    BigDecimal getServiceCost(int idService);
    MoikaService getMoikaService(int idService);
    MoikaServiceAdditinalInfo getAdditionalMoikaServiceInfo(int idService);
}
