package com.trl.users.repository.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
@Accessors(chain = true)
@Entity
@Table(name = "usr")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = BankDataEntity.class)
    private Set<BankDataEntity> bankData;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = AddressEntity.class)
    private Set<AddressEntity> address;

    private LocalDate birthday;

}
