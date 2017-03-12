package io.khasang.moika.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static io.khasang.moika.util.DataValidationPatterns.EMAIL_PATTERN;
import static io.khasang.moika.util.DataValidationPatterns.PHONE_NUMBER_PATTERN;
import static javax.swing.text.StyleConstants.Size;

@Entity
@Table(name = "users")
public class User extends ABaseMoikaEntity implements Serializable {

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 4, max = 16)
    @NaturalId(mutable = true)
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @NotNull
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id_role"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role_id"})
    )
    private Set<Role> roles = new HashSet<>();

    private boolean enabled;

    @NotNull
    @Size(min = 1, max = 32)
    @Column(nullable = false)
    private String firstName;

    private String middleName;
    
    private String lastName;

    private Date birthday;

    @NotNull
    @Pattern(regexp = PHONE_NUMBER_PATTERN)
    @Size(min = 1, max = 32)
    private String phone;

    @NotNull
    @Pattern(regexp = EMAIL_PATTERN)
    @Column(nullable = false, unique = true)
    private String email;

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
