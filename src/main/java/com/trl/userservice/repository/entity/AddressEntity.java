package com.trl.userservice.repository.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "houseNumber", nullable = false)
    private String houseNumber;

    @Column(name = "postcode", nullable = false)
    private Integer postcode;

    @ManyToOne
    @JoinColumn(name = "usr_id", updatable = false, nullable = false)
    private UserEntity user;

    public AddressEntity() {
    }

    public AddressEntity(Long id, String country, String city, String street, String houseNumber, Integer postcode) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postcode = postcode;
    }

    public AddressEntity(Long id, String country, String city, String street, String houseNumber, Integer postcode, UserEntity user) {
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

    public Integer getPostcode() {
        return postcode;
    }

    public UserEntity getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
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
        return "AddressEntity{" +
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
