package com.trl.userservice.service;

import com.trl.userservice.controller.dto.BankDataDTO;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * This interface is designed to support service for {@literal BankDataDTO}.
 *
 * @author Tsyupryk Roman
 */
public interface BankDataService {

    BankDataDTO add(Long userId, BankDataDTO bankData);


    BankDataDTO getByBankDataId(Long bankDataId);

    Page<BankDataDTO> getPageOfBankDataByUserId(Long userId, Integer startPage, Integer pageSize);

    Page<BankDataDTO> getPageOfSortedBankDataByUserId(Long userId, Integer startPage, Integer pageSize, String sortOrder);


    BankDataDTO updateByBankDataId(Long bankDataId, BankDataDTO bankData);


    BankDataDTO deleteByBankDataId(Long bankDataId);

    List<BankDataDTO> deleteAllBankDataByUserId(Long userId);
}