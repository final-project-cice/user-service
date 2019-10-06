package com.trl.userservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Objects;

public class BankDataDTO {

    private Long id;
    private String bankAccountNumber;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateOfExpiry;

    private Integer cvi;
    private UserDTO user;

    public BankDataDTO() {
    }

    public BankDataDTO(Long id, String bankAccountNumber, LocalDate dateOfExpiry, Integer cvi) {
        this.id = id;
        this.bankAccountNumber = bankAccountNumber;
        this.dateOfExpiry = dateOfExpiry;
        this.cvi = cvi;
    }

    public BankDataDTO(Long id, String bankAccountNumber, LocalDate dateOfExpiry, Integer cvi, UserDTO user) {
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

    public UserDTO getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankDataDTO that = (BankDataDTO) o;
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
        return "BankDataDTO{" +
                "id=" + id +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", dateOfExpiry=" + dateOfExpiry +
                ", cvi=" + cvi +
                ", user=" + user +
                '}';
    }

}
