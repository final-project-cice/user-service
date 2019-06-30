package com.trl.users.repository;

import com.trl.users.repository.entity.BankDataEntity;
import com.trl.users.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *
 */
public interface BankDataRepository extends JpaRepository<BankDataEntity, Long> {

    /**
     * @param user
     */
    @Modifying
    @Query("DELETE FROM BankDataEntity e WHERE e.userId=:user")
    void deleteByUser(UserEntity user);


    /**
     * @param user
     * @return
     */
    @Query("SELECT new BankDataEntity (e.id, e.bankAccountNumber, e.dateOfExpiry, e.cvi, e.userId) FROM BankDataEntity e WHERE e.userId=:user")
    List<BankDataEntity> findByUser(@Param(value = "user") UserEntity user);

}
