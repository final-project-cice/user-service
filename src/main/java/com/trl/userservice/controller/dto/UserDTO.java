package com.trl.userservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * This class is designed to represent DTO object of user.
 *
 * @author Tsyupryk Roman
 */
public class UserDTO extends ResourceSupport {

    private Long userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private List<BankDataDTO> bankData;
    private List<AddressDTO> address;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthday;

    public UserDTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public List<BankDataDTO> getBankData() {
        return bankData;
    }

    public void setBankData(List<BankDataDTO> bankData) {
        this.bankData = bankData;
    }

    public void addBankData(BankDataDTO bankDataDTO) {
        this.bankData.add(bankDataDTO);
        bankDataDTO.setUser(this);
    }

    public void addBankDataList(List<BankDataDTO> bankDataList) {
        bankDataList.forEach(this::addBankData);
    }

    public void removeBankData(BankDataDTO bankDataDTO) {
        this.bankData.remove(bankDataDTO);
        bankDataDTO.setUser(null);
    }

    public List<AddressDTO> getAddress() {
        return address;
    }

    public void setAddress(List<AddressDTO> address) {
        this.address = address;
    }

    public void addAddress(AddressDTO addressDTO) {
        this.address.add(addressDTO);
        addressDTO.setUser(this);
    }

    public void addAddressList(List<AddressDTO> addressList) {
        addressList.forEach(this::addAddress);
    }

    public void removeAddress(AddressDTO addressDTO) {
        this.address.remove(addressDTO);
        addressDTO.setUser(null);
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
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(userId, userDTO.userId) &&
                Objects.equals(firstName, userDTO.firstName) &&
                Objects.equals(lastName, userDTO.lastName) &&
                Objects.equals(userName, userDTO.userName) &&
                Objects.equals(email, userDTO.email) &&
                Objects.equals(password, userDTO.password) &&
                Objects.equals(bankData, userDTO.bankData) &&
                Objects.equals(address, userDTO.address) &&
                Objects.equals(birthday, userDTO.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, userName, email, password, bankData, address, birthday);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + userId +
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