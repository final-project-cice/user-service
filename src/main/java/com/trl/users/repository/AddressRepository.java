package com.trl.users.repository;

import com.trl.users.controller.dto.AddressDTO;
import com.trl.users.repository.entity.AddressEntity;
import com.trl.users.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    /**
     * @param user
     */
    @Modifying
    @Query("DELETE FROM AddressEntity e WHERE e.user=:user")
    void deleteByUser(UserEntity user);



    /**
     * @param user
     * @return
     */
    @Query("SELECT new AddressEntity(e.id, e.country, e.city, e.street, e.houseNumber, e.postcode) FROM AddressEntity e WHERE e.user=:user")
    List<AddressEntity> findByUser(@Param(value = "user") UserEntity user);

    @Query("SELECT new AddressEntity(e.id, e.country, e.city, e.street, e.houseNumber, e.postcode) FROM AddressEntity e WHERE e.id=:id")
    Optional<AddressEntity> findById(@Param(value = "id") Long id);



    @Modifying(clearAutomatically = true)
    @Query("update AddressEntity e set e.country=:country where e.id =:id")
    void updateCountry(@Param("id") Long id, @Param("country") String country);

    @Modifying(clearAutomatically = true)
    @Query("update AddressEntity e set e.city=:city where e.id =:id")
    void updateCity(@Param("id") Long id, @Param("city") String city);

    @Modifying(clearAutomatically = true)
    @Query("update AddressEntity e set e.street=:street where e.id =:id")
    void updateStreet(@Param("id") Long id, @Param("street") String street);

    @Modifying(clearAutomatically = true)
    @Query("update AddressEntity e set e.houseNumber=:houseNumber where e.id =:id")
    void updateHouseNumber(@Param("id") Long id, @Param("houseNumber") String houseNumber);

    @Modifying(clearAutomatically = true)
    @Query("update AddressEntity e set e.postcode=:postcode where e.id =:id")
    void updatePostcode(@Param("id") Long id, @Param("postcode") Long postcode);

}
