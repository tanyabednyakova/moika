package io.khasang.moika.entity;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity(name = "Queue_t")
public class Queue_t {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dayServise;

    @Temporal(TemporalType.DATE)
    private Date timeServise;

    @ManyToOne
    @JoinColumn(name = "id_car", foreignKey = @ForeignKey(name = "fk_id_car"), insertable=false, updatable=false )
    private Car car;

    @ManyToOne
    @JoinColumn(name = "id_client", foreignKey = @ForeignKey(name = "fk_car_type"), insertable=false, updatable=false )
    private Client client;

    public Queue_t(Calendar dayServise, Date timeServise, Car car, Client client) {
        this.dayServise = dayServise;
        this.timeServise = timeServise;
        this.car = car;
        this.client = client;
    }

    public Queue_t() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Calendar getDayServise() {
        return dayServise;
    }

    public void setDayServise(Calendar dayServise) {
        this.dayServise = dayServise;
    }

    public Date getTimeServise() {
        return timeServise;
    }

    public void setTimeServise(Date timeServise) {
        this.timeServise = timeServise;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
