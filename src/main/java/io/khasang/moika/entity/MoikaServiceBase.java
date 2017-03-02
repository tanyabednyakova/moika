package io.khasang.moika.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "service")
public class MoikaServiceBase implements MoikaService {
/*
    id_fclt integer NOT NULL,
    id_type integer NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    is_pack boolean NOT NULL DEFAULT false,
    id_status smallint NOT NULL DEFAULT 0,
    description character varying(255) COLLATE pg_catalog."default",
    time_create timestamp without time zone,
    */

    @Id
    @Column(name = "id_service", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_fclt")
    private int idFacility;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_fclt", foreignKey = @ForeignKey(name = "fk_box_facility"), insertable=false, updatable=false )
    private WashFacility washFacility;

    @Column(name = "id_type")
    private int idType;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn( name = "id_type", foreignKey = @ForeignKey(name = "fk_service_type"), insertable=false, updatable=false )
    private ServiceType serviceTypeEntity;

    @Column(name = "id_status")
    private Short idStatus;

    @ManyToOne
    @JoinColumn(name = "id_status", foreignKey = @ForeignKey(name = "fk_service_status"), insertable=false, updatable=false )
    private ServiceStatus serviceStatusEntity;

    @Column(name = "name", unique = true)
    private String serviceName;

    @Column(name = "description")
    private String description;


    protected MoikaServiceBase(){}

    @Override
    public MoikaService createMoikaService() {
       if (this == null) new MoikaServiceBase();
       return this;
    }

    @Override
    public int getMoikaServiceId() {
        return this.id;
    }

    @Override
    public String getMoikaServiceType() {
        return serviceStatusEntity.getCode();
    }

    @Override
    public void setMoikaServiceType(int type) {
        this.idType = type;
    }

    @Override
    public void setMoikaServiceType(String code) {
        ServiceStatus sse = new ServiceStatus();
        sse.setCode(code);
        this.idType = serviceStatusEntity.getId();
    }

    @Override
    public BigDecimal getServiceCost(int idService) {
        return null;
    }

    @Override
    public MoikaService getMoikaService(int idService) {
        return null;
    }

    @Override
    public MoikaServiceAdditinalInfo getAdditionalMoikaServiceInfo(int idService) {
        return null;
    }


}
