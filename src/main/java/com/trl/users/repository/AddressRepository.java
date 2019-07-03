package com.trl.users.repository;

import com.trl.users.repository.entity.AddressEntity;
import com.trl.users.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

/**
 *
 */
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {


    /**
     * @param id
     * @param country
     */
    @Modifying(clearAutomatically = true)
    @Query("update AddressEntity e set e.country=:country where e.id =:id")
    void updateCountry(@Param("id") Long id, @Param("country") String country);

    /**
     * @param id
     * @param city
     */
    @Modifying(clearAutomatically = true)
    @Query("update AddressEntity e set e.city=:city where e.id =:id")
    void updateCity(@Param("id") Long id, @Param("city") String city);

    /**
     * @param id
     * @param street
     */
    @Modifying(clearAutomatically = true)
    @Query("update AddressEntity e set e.street=:street where e.id =:id")
    void updateStreet(@Param("id") Long id, @Param("street") String street);

    /**
     * @param id
     * @param houseNumber
     */
    @Modifying(clearAutomatically = true)
    @Query("update AddressEntity e set e.houseNumber=:houseNumber where e.id =:id")
    void updateHouseNumber(@Param("id") Long id, @Param("houseNumber") String houseNumber);

    /**
     * @param id
     * @param postcode
     */
    @Modifying(clearAutomatically = true)
    @Query("update AddressEntity e set e.postcode=:postcode where e.id =:id")
    void updatePostcode(@Param("id") Long id, @Param("postcode") Long postcode);


    /**
     * @param user
     */
    @Modifying
    @Query("DELETE FROM AddressEntity e WHERE e.user=:user")
    void deleteByUser(UserEntity user);


    /**
     * @param id
     * @return
     */
    @Query("SELECT new AddressEntity(e.id, e.country, e.city, e.street, e.houseNumber, e.postcode) FROM AddressEntity e WHERE e.id=:id")
    Optional<AddressEntity> findById(@Param(value = "id") Long id);

    /**
     * @param country
     * @return
     */
    @Query("SELECT new AddressEntity(e.id, e.country, e.city, e.street, e.houseNumber, e.postcode) FROM AddressEntity e WHERE e.country=:country")
    Set<AddressEntity> findByCountry(@Param(value = "country") String country);

    /**
     * @param city
     * @return
     */
    @Query("SELECT new AddressEntity(e.id, e.country, e.city, e.street, e.houseNumber, e.postcode) FROM AddressEntity e WHERE e.city=:city")
    Set<AddressEntity> findByCity(@Param(value = "city") String city);

    /**
     * @param street
     * @return
     */
    @Query("SELECT new AddressEntity(e.id, e.country, e.city, e.street, e.houseNumber, e.postcode) FROM AddressEntity e WHERE e.street=:street")
    Set<AddressEntity> findByStreet(@Param(value = "street") String street);

    /**
     * @param houseNumber
     * @return
     */
    @Query("SELECT new AddressEntity(e.id, e.country, e.city, e.street, e.houseNumber, e.postcode) FROM AddressEntity e WHERE e.houseNumber=:houseNumber")
    Set<AddressEntity> findByHouseNumber(@Param(value = "houseNumber") String houseNumber);

    /**
     * @param postcode
     * @return
     */
    @Query("SELECT new AddressEntity(e.id, e.country, e.city, e.street, e.houseNumber, e.postcode) FROM AddressEntity e WHERE e.postcode=:postcode")
    Set<AddressEntity> findByPostcode(@Param(value = "postcode") Long postcode);

    /**
     * @param user
     * @return
     */
    @Query("SELECT new AddressEntity(e.id, e.country, e.city, e.street, e.houseNumber, e.postcode) FROM AddressEntity e WHERE e.user=:user")
    Set<AddressEntity> findByUser(@Param(value = "user") UserEntity user);

}
