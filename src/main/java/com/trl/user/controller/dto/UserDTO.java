package com.trl.user.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<BankDataDTO> bankData;
    private Set<AddressDTO> address;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthday;

    public UserDTO() {
    }

    public UserDTO(Long id, String firstName, String lastName, String email, String password, LocalDate birthday) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
    }

    public UserDTO(Long id, String firstName, String lastName, String email, String password, Set<BankDataDTO> bankData, Set<AddressDTO> address, LocalDate birthday) {
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

    public Set<BankDataDTO> getBankData() {
        return bankData;
    }

    public Set<AddressDTO> getAddress() {
        return address;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return id.equals(userDTO.id) &&
                firstName.equals(userDTO.firstName) &&
                lastName.equals(userDTO.lastName) &&
                email.equals(userDTO.email) &&
                password.equals(userDTO.password) &&
                Objects.equals(bankData, userDTO.bankData) &&
                Objects.equals(address, userDTO.address) &&
                birthday.equals(userDTO.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, password, bankData, address, birthday);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
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