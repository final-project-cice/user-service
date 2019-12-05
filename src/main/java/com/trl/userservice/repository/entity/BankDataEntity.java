package com.trl.userservice.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * This class is designed to represent Entity object of bank data of user.
 *
 * @author Tsyupryk Roman
 */
@Entity(name = "BankDataEntity")
@Table(name = "bank_data_user")
public class BankDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "bank_account_number", updatable = false, nullable = false)
    private String bankAccountNumber;

    @Column(name = "date_of_expiry", updatable = false, nullable = false)
    private LocalDate dateOfExpiry;

    @Column(name = "cvi", updatable = false, nullable = false)
    private Integer cvi;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usr_id")
    private UserEntity user;

    public BankDataEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public LocalDate getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(LocalDate dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public Integer getCvi() {
        return cvi;
    }

    public void setCvi(Integer cvi) {
        this.cvi = cvi;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankDataEntity that = (BankDataEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(bankAccountNumber, that.bankAccountNumber) &&
                Objects.equals(dateOfExpiry, that.dateOfExpiry) &&
                Objects.equals(cvi, that.cvi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bankAccountNumber, dateOfExpiry, cvi);
    }

    @Override
    public String toString() {
        return "BankDataEntity{" +
                "id=" + id +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", dateOfExpiry=" + dateOfExpiry +
                ", cvi=" + cvi +
                '}';
    }
}
