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

}
