package com.trl.user.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;
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

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthday;

}