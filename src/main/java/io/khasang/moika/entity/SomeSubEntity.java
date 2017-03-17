package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="some_sub_entities")
public class SomeSubEntity extends ABaseMoikaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String name;
    private String content;
    @Column(name = "some_id",insertable = false,updatable = false)
    private long someId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "some_id",insertable = false,updatable = false)
    @JsonIgnore
    private SomeEntity someEntity;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getSomeId() {
        return someId;
    }

    public void setSomeId(long someId) {
        this.someId = someId;
    }

    public SomeEntity getSomeEntity() {
        return someEntity;
    }

    public void setSomeEntity(SomeEntity someEntity) {
        this.someEntity = someEntity;
    }
}
