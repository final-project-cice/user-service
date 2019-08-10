package com.trl.user.repository.entity;

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
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = " bankAccountNumber", updatable = false, nullable = false)
    private String bankAccountNumber;

    @Column(name = " dateOfExpiry", updatable = false, nullable = false)
    private LocalDate dateOfExpiry;

    @Column(name = "cvi", updatable = false, nullable = false)
    private Integer cvi;

    @ManyToOne
    @JoinColumn(name = "usr_id", updatable = false, nullable = false)
    private UserEntity user;

    public BankDataEntity(Long id, String bankAccountNumber, LocalDate dateOfExpiry, Integer cvi) {
        this.id = id;
        this.bankAccountNumber = bankAccountNumber;
        this.dateOfExpiry = dateOfExpiry;
        this.cvi = cvi;
    }

}
