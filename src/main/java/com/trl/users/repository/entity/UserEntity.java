package com.trl.users.repository.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
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
public class UserEntity implements Serializable, Comparable<UserEntity> {

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

    @Basic
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Override
    public int compareTo(UserEntity o) {
        return Long.compare(this.id, o.id);
    }

}
