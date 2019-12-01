package com.trl.userservice.repository;

import com.trl.userservice.repository.entity.AddressEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This interface is designed to support JPA for {@literal AddressEntity}.
 *
 * @author Tsyupryk Roman
 */
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO address_user (id, country, city, street, house_number, postcode, usr_id) " +
            "VALUES (:id, :country, :city, :street, :houseNumber, :postcode, :userId)", nativeQuery = true)
    void add(@Param("id") Long id, @Param("country") String country, @Param("city") String city, @Param("street")
            String street, @Param("houseNumber") String houseNumber, @Param("postcode") Integer postcode, @Param("userId") Long userId);


    @Query(value = "SELECT e FROM AddressEntity e WHERE e.user.id=:userId")
    List<AddressEntity> findByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT e FROM AddressEntity e WHERE e.user.id=:userId")
    Page<AddressEntity> getPageOfAddressesByUserId(@Param("userId") Long userId, Pageable pageable);


    @Transactional
    @Modifying
    @Query("DELETE FROM AddressEntity e WHERE e.user.id=:userId")
    void deleteAllAddressesByUserId(@Param("userId") Long userId);
}
