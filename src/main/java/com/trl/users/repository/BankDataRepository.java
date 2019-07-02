package com.trl.users.repository;

import com.trl.users.repository.entity.BankDataEntity;
import com.trl.users.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

/**
 *
 */
public interface BankDataRepository extends JpaRepository<BankDataEntity, Long> {

    /**
     * @param user
     */
    @Modifying
    @Query("DELETE FROM BankDataEntity e WHERE e.user=:user")
    void deleteByUser(UserEntity user);


    /**
     * @param user
     * @return
     */
    @Query("SELECT new BankDataEntity (e.id, e.bankAccountNumber, e.dateOfExpiry, e.cvi) FROM BankDataEntity e WHERE e.user=:user")
    Set<BankDataEntity> findByUser(@Param(value = "user") UserEntity user);

}
