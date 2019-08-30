package com.trl.user.controller.dto;

import java.util.Objects;

public class AddressDTO {

    private Long id;
    private String country;
    private String city;
    private String street;
    private String houseNumber;
    private Long postcode;
    private UserDTO user;

    public AddressDTO() {
    }

    public AddressDTO(Long id, String country, String city, String street, String houseNumber, Long postcode) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postcode = postcode;
    }

    public AddressDTO(Long id, String country, String city, String street, String houseNumber, Long postcode, UserDTO user) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postcode = postcode;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public Long getPostcode() {
        return postcode;
    }

    public UserDTO getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDTO that = (AddressDTO) o;
        return id.equals(that.id) &&
                country.equals(that.country) &&
                city.equals(that.city) &&
                street.equals(that.street) &&
                houseNumber.equals(that.houseNumber) &&
                postcode.equals(that.postcode) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city, street, houseNumber, postcode, user);
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", postcode=" + postcode +
                ", user=" + user +
                '}';
    }

}
