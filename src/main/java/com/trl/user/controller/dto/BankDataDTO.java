package com.trl.user.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@Accessors(chain = true)
public class BankDataDTO {

    private Long id;
    private String bankAccountNumber;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateOfExpiry;

    private Integer cvi;
    private UserDTO user;

    public BankDataDTO(Long id, String bankAccountNumber, LocalDate dateOfExpiry, Integer cvi) {
        this.id = id;
        this.bankAccountNumber = bankAccountNumber;
        this.dateOfExpiry = dateOfExpiry;
        this.cvi = cvi;
    }

}
