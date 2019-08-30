package com.trl.user.repository.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "bank_data")
public class BankDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = " bankAccountNumber", updatable = false, nullable = false)
    private String bankAccountNumber;

    @Column(name = " dateOfExpiry", updatable = false, nullable = false)
    private LocalDate dateOfExpiry;

    @Column(name = "cvi", updatable = false, nullable = false)
    private Integer cvi;

    @ManyToOne
    @JoinColumn(name = "usr_id", updatable = false, nullable = false)
    private UserEntity user;

    public BankDataEntity() {
    }

    public BankDataEntity(Long id, String bankAccountNumber, LocalDate dateOfExpiry, Integer cvi) {
        this.id = id;
        this.bankAccountNumber = bankAccountNumber;
        this.dateOfExpiry = dateOfExpiry;
        this.cvi = cvi;
    }

    public BankDataEntity(Long id, String bankAccountNumber, LocalDate dateOfExpiry, Integer cvi, UserEntity user) {
        this.id = id;
        this.bankAccountNumber = bankAccountNumber;
        this.dateOfExpiry = dateOfExpiry;
        this.cvi = cvi;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public LocalDate getDateOfExpiry() {
        return dateOfExpiry;
    }

    public Integer getCvi() {
        return cvi;
    }

    public UserEntity getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankDataEntity that = (BankDataEntity) o;
        return id.equals(that.id) &&
                bankAccountNumber.equals(that.bankAccountNumber) &&
                dateOfExpiry.equals(that.dateOfExpiry) &&
                cvi.equals(that.cvi) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bankAccountNumber, dateOfExpiry, cvi, user);
    }

    @Override
    public String toString() {
        return "BankDataEntity{" +
                "id=" + id +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", dateOfExpiry=" + dateOfExpiry +
                ", cvi=" + cvi +
                ", user=" + user +
                '}';
    }

}
