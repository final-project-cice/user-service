package com.trl.users.repository.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@Accessors(chain = true)
@Entity
@Table(name = "bank_data")
public class BankDataEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bankAccountNumber;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date dateOfExpiry;

    private Integer cvi;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "usr_id")
    private UserEntity user;

    public BankDataEntity(Long id, String bankAccountNumber, Date dateOfExpiry, Integer cvi) {
        this.id = id;
        this.bankAccountNumber = bankAccountNumber;
        this.dateOfExpiry = dateOfExpiry;
        this.cvi = cvi;
    }

}
