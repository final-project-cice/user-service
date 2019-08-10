package com.trl.user.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
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