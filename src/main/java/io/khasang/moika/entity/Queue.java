package io.khasang.moika.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;

@Entity
public class Queue {
    @Id
    private long id;
    private long order_id;
    private java.sql.Time start_time;
    private java.sql.Time finish_time;
    private java.sql.Date date_start;

    public Queue() {
    }

    public long getId() {
        return id;
    }

    public long getOrder_id() {
        return order_id;
    }

    public Time getStart_time() {
        return start_time;
    }

    public Time getFinish_time() {
        return finish_time;
    }

    public Date getDate_start() {
        return date_start;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public void setStart_time(Time start_time) {
        this.start_time = start_time;
    }

    public void setFinish_time(Time finish_time) {
        this.finish_time = finish_time;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }
}
