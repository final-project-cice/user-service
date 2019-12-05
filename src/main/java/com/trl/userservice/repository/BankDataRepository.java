package com.trl.userservice.repository;

import com.trl.userservice.repository.entity.BankDataEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * This interface is designed to support JPA for {@literal BankDataEntity}.
 *
 * @author Tsyupryk Roman
 */
public interface BankDataRepository extends JpaRepository<BankDataEntity, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO bank_data_user (id, bank_account_number, date_of_expiry, cvi, usr_id) VALUES (:id, :bankAccountNumber, :dateOfExpiry, :cvi, :userId)", nativeQuery = true)
    void add(@Param("id") Long id, @Param("bankAccountNumber") String bankAccountNumber, @Param("dateOfExpiry") LocalDate dateOfExpiry, @Param("cvi") Integer cvi, @Param("userId") Long userId);


    @Query(value = "SELECT e FROM BankDataEntity e WHERE e.user.id=:userId")
    List<BankDataEntity> findByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT e FROM BankDataEntity e WHERE e.user.id=:userId")
    Page<BankDataEntity> getPageOfBankDataByUserId(@Param("userId") Long userId, Pageable pageable);


    @Transactional
    @Modifying
    @Query("DELETE FROM BankDataEntity e WHERE e.user.id=:userId")
    void deleteAllBankDataByUserId(@Param("userId") Long userId);
}
