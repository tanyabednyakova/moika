package io.khasang.moika.entity;

import java.math.BigDecimal;

public interface MoikaService  {
   // MoikaService createMoikaService(); //
    int getId();
    int getServiceType();
    void setServiceType(int type);
    void setServiceType(String code);
    int getServiceStatus();
    void setServiceStatus(short status);
    void setServiceStatus(String code);
    BigDecimal getServiceCost();
    void setServiceCost(BigDecimal cost);
    int getIdFacility();
    void setIdFacility(int idFacility);
    String getServiceName();
    void setServiceName(String serviceName);
    String getDescription();
    void setDescription(String description);

}
