package com.trl.userservice.repository;

import com.trl.userservice.repository.entity.BankDataEntity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface is designed to support JPA for {@literal BankDataEntity}.
 *
 * @author Tsyupryk Roman
 */
public interface BankDataRepository extends JpaRepository<BankDataEntity, Long> {


}
