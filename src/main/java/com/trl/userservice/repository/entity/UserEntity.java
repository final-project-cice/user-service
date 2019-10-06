package com.trl.userservice.repository.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "usr")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = " password", nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = BankDataEntity.class)
    private Set<BankDataEntity> bankData;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = AddressEntity.class)
    private Set<AddressEntity> address;

    @Column(name = " birthday", nullable = false)
    private LocalDate birthday;

    public UserEntity() {
    }

    public UserEntity(Long id, String firstName, String lastName, String email, String password, LocalDate birthday) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
    }

    public UserEntity(Long id, String firstName, String lastName, String email, String password, Set<BankDataEntity> bankData, Set<AddressEntity> address, LocalDate birthday) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.bankData = bankData;
        this.address = address;
        this.birthday = birthday;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<BankDataEntity> getBankData() {
        return bankData;
    }

    public Set<AddressEntity> getAddress() {
        return address;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id.equals(that.id) &&
                firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) &&
                email.equals(that.email) &&
                password.equals(that.password) &&
                Objects.equals(bankData, that.bankData) &&
                Objects.equals(address, that.address) &&
                birthday.equals(that.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, password, bankData, address, birthday);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", bankData=" + bankData +
                ", address=" + address +
                ", birthday=" + birthday +
                '}';
    }

}
