package io.khasang.moika.entity;

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
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "somes_subsomes", joinColumns = @JoinColumn(name = "sub_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "some_id",referencedColumnName = "id"))
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

    public SomeEntity getSomeEntity() {
        return someEntity;
    }

    public void setSomeEntity(SomeEntity someEntity) {
        this.someEntity = someEntity;
    }
}
