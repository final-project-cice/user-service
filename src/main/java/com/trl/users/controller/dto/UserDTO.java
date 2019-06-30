package com.trl.users.controller.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.trl.users.service.deserializer.CustomerDateDeserializer;
import com.trl.users.service.serializer.CustomerDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<BankDataDTO> bankData;
    private Set<AddressDTO> address;

    @JsonSerialize(using = CustomerDateSerializer.class)
    @JsonDeserialize(using = CustomerDateDeserializer.class)
    private Date birthday;

}