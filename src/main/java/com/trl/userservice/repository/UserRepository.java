package com.trl.userservice.repository;

import com.trl.userservice.repository.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * This interface is designed to support JPA for {@literal UserEntity}.
 *
 * @author Tsyupryk Roman
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO usr (id, first_name, last_name, user_name, email, password, birthday) " +
            "VALUES (:id, :firstName, :lastName, :userName, :email, :password, :birthday)", nativeQuery = true)
    void add(@Param("id") Long id, @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("userName")
            String userName, @Param("email") String email, @Param("password") String password, @Param("birthday") LocalDate birthday);

}
