package com.trl.users.repository.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@Accessors(chain = true)
@Entity
@Table(name = "bank_data")
public class BankDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bankAccountNumber;
    private LocalDate dateOfExpiry;
    private Integer cvi;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "usr_id")
    private UserEntity user;

    public BankDataEntity(Long id, String bankAccountNumber, LocalDate dateOfExpiry, Integer cvi) {
        this.id = id;
        this.bankAccountNumber = bankAccountNumber;
        this.dateOfExpiry = dateOfExpiry;
        this.cvi = cvi;
    }

}
