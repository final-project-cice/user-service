package com.trl.userservice.repository;

import com.trl.userservice.repository.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface is designed to support JPA for {@literal UserEntity}.
 *
 * @author Tsyupryk Roman
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
