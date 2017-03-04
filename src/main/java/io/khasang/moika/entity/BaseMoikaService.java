package io.khasang.moika.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "service")
@Inheritance(strategy=InheritanceType.JOINED)
public  class BaseMoikaService extends ABaseMoikaEntity {
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


    protected BaseMoikaService(){}


    public int getId() {
        return this.id;
    }


    public int getServiceType() {
        return idType;
    }


    public void setServiceType(int type) {
        this.idType = type;
    }


    public void setServiceType(String code) {
        ServiceType sse = new ServiceType();
        sse.getTypeCode();
        this.serviceTypeEntity = sse;
    }



    public int getServiceStatus() {
        return idStatus;
    }


    public void setServiceStatus(short status) {
        this.idStatus = status;
    }


    public void setServiceStatus(String code) {
        ServiceStatus st = new ServiceStatus();
        st.setCode(code);
        this.serviceStatusEntity = st;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdFacility() {
        return idFacility;
    }

    public void setIdFacility(int idFacility) {
        this.idFacility = idFacility;
    }


}
