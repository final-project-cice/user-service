package com.trl.user.repository.entity;

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
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = " password", nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = BankDataEntity.class)
    @Column(name = "bankData", updatable = false, nullable = false)
    private Set<BankDataEntity> bankData;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = AddressEntity.class)
    @Column(name = "address", updatable = false, nullable = false)
    private Set<AddressEntity> address;

    @Column(name = " birthday", nullable = false)
    private LocalDate birthday;

}
