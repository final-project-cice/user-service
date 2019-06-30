package com.trl.users.repository;

import com.trl.users.repository.entity.AddressEntity;
import com.trl.users.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *
 */
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    /**
     * @param user
     */
    @Modifying
    @Query("DELETE FROM AddressEntity e WHERE e.userId=:user")
    void deleteByUser(UserEntity user);

    /**
     * @param user
     * @return
     */
    @Query("SELECT new AddressEntity(e.id, e.country, e.city, e.street, e.houseNumber, e.postcode, e.userId) FROM AddressEntity e WHERE e.userId=:user")
    List<AddressEntity> findByUser(@Param(value = "user") UserEntity user);

}
