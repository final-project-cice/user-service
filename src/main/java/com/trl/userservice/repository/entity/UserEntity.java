package com.trl.userservice.repository.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * This class is designed to represent Entity object of user.
 *
 * @author Tsyupryk Roman
 */
@Entity(name = "UserEntity")
@Table(name = "usr")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BankDataEntity> bankData;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AddressEntity> address;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<BankDataEntity> getBankData() {
        return bankData;
    }

    public void setBankData(List<BankDataEntity> bankData) {
        this.bankData = bankData;
    }

    public void addBankData(BankDataEntity bankDataEntity) {
        this.bankData.add(bankDataEntity);
        bankDataEntity.setUser(this);
    }

    public void addBankDataList(List<BankDataEntity> bankDataList) {
        bankDataList.forEach(this::addBankData);
    }

    public void removeBankData(BankDataEntity bankDataEntity) {
        this.bankData.remove(bankDataEntity);
        bankDataEntity.setUser(null);
    }

    public List<AddressEntity> getAddress() {
        return address;
    }

    public void setAddress(List<AddressEntity> address) {
        this.address = address;
    }

    public void addAddress(AddressEntity addressEntity) {
        this.address.add(addressEntity);
        addressEntity.setUser(this);
    }

    public void addAddressList(List<AddressEntity> addressList) {
        addressList.forEach(this::addAddress);
    }

    public void removeAddress(AddressEntity addressEntity) {
        this.address.remove(addressEntity);
        addressEntity.setUser(null);
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(bankData, that.bankData) &&
                Objects.equals(address, that.address) &&
                Objects.equals(birthday, that.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, userName, email, password, bankData, address, birthday);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", bankData=" + bankData +
                ", address=" + address +
                ", birthday=" + birthday +
                '}';
    }
}