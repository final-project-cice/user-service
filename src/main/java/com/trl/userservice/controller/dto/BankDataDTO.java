package com.trl.userservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDate;
import java.util.Objects;

public class BankDataDTO extends ResourceSupport {

    private Long bankDataId;
    private String bankAccountNumber;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateOfExpiry;

    private Integer cvi;

    @JsonIgnore
    private UserDTO user;

    public BankDataDTO() {
    }

    public Long getBankDataId() {
        return bankDataId;
    }

    public void setBankDataId(Long bankDataId) {
        this.bankDataId = bankDataId;
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankDataDTO that = (BankDataDTO) o;
        return Objects.equals(bankDataId, that.bankDataId) &&
                Objects.equals(bankAccountNumber, that.bankAccountNumber) &&
                Objects.equals(dateOfExpiry, that.dateOfExpiry) &&
                Objects.equals(cvi, that.cvi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankDataId, bankAccountNumber, dateOfExpiry, cvi);
    }

    @Override
    public String toString() {
        return "BankDataDTO{" +
                "id=" + bankDataId +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", dateOfExpiry=" + dateOfExpiry +
                ", cvi=" + cvi +
                '}';
    }
}