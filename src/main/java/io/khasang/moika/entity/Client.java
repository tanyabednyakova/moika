package io.khasang.moika.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "clients")
public class Client {

    @Id
    @Column(name = "id_client", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastname;
    @Column(name = "tel")
    private String tel;
    @Column(name = "status", nullable = false)
    private int status;
    @Column(name = "date_reg")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateReg;
    @Column(name = "dateLastWash")
    @Temporal(TemporalType.DATE)
    private Date dateLastWash;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "r_client_car", joinColumns = {
            @JoinColumn(name = "id_client", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "id_car",
                    nullable = false, updatable = false) })
    private List<Car> cars = new ArrayList<>();

    public Client() {
    }

    public Client(String firstName, String middleName, String lastname, String tel) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastname = lastname;
        this.tel = tel;
    }

    public Client(String firstName, String middleName, String lastname, String tel, List<Car> cars) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastname = lastname;
        this.tel = tel;
        this.cars = cars;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDateReg() {
        return dateReg;
    }

    public Date getDateLastWash() {
        return dateLastWash;
    }

    public void setDateLastWash(Date dateLastWash) {
        this.dateLastWash = dateLastWash;
    }

    public List<Car> getCars() {
        return this.cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public void addCar(Car car){
        getCars().add(car);
    }

    public void removeCar(Car car){
        getCars().remove(car);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastname='" + lastname + '\'' +
                ", tel='" + tel + '\'' +
                ", dateReg=" + dateReg +
                ", status=" + status +
                ", dateLastWash=" + dateLastWash +
                '}';
    }
}
