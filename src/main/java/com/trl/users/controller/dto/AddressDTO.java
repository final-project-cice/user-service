package com.trl.users.controller.dto;

import lombok.*;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
@Accessors(chain = true)
public class AddressDTO {

    private Long id;
    private String country;
    private String city;
    private String street;
    private String houseNumber;
    private int postcode;
    private UserDTO user;

    public AddressDTO(Long id, String country, String city, String street, String houseNumber, int postcode) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postcode = postcode;
    }
}
