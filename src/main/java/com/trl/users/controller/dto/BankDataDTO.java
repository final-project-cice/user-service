package com.trl.users.controller.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.trl.users.service.deserializer.CustomerDateDeserializer;
import com.trl.users.service.serializer.CustomerDateSerializer;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@Accessors(chain = true)
public class BankDataDTO implements Comparable<BankDataDTO>{

    private Long id;
    private String bankAccountNumber;

    @JsonSerialize(using = CustomerDateSerializer.class)
    @JsonDeserialize(using = CustomerDateDeserializer.class)
    private Date dateOfExpiry;

    private Integer cvi;
    private UserDTO user;

    public BankDataDTO(Long id, String bankAccountNumber, Date dateOfExpiry, Integer cvi) {
        this.id = id;
        this.bankAccountNumber = bankAccountNumber;
        this.dateOfExpiry = dateOfExpiry;
        this.cvi = cvi;
    }

    @Override
    public int compareTo(BankDataDTO o) {
        return Long.compare(this.id, o.id);
    }

}
