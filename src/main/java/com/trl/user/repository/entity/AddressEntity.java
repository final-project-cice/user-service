package com.trl.user.repository.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
@Accessors(chain = true)
@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "houseNumber", nullable = false)
    private String houseNumber;

    @Column(name = "postcode", nullable = false)
    private Long postcode;

    @ManyToOne
    @JoinColumn(name = "usr_id", updatable = false, nullable = false)
    private UserEntity user;

    public AddressEntity(Long id, String country, String city, String street, String houseNumber, Long postcode) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postcode = postcode;
    }

}
