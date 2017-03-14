package io.khasang.moika.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="some_entities")
public class SomeEntity extends ABaseMoikaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Column(unique = true)
    private String name;
    @NotNull
    private Duration interval;

    /*@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "somes_subsomes", joinColumns = @JoinColumn(name = "some_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "sub_id",referencedColumnName = "id"))
    private List<SomeSubEntity> subEntityList;//*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Duration getInterval() {
        return interval;
    }

    public void setInterval(Duration interval) {
        this.interval = interval;
    }
/*
    public List<SomeSubEntity> getSubEntityList() {
        return subEntityList;
    }

    public void setSubEntityList(List<SomeSubEntity> subEntityList) {
        this.subEntityList = subEntityList;
    }//*/
}
