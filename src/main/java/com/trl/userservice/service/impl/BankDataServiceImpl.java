package com.trl.userservice.service.impl;

import com.trl.userservice.controller.dto.BankDataDTO;
import com.trl.userservice.exceptions.*;
import com.trl.userservice.repository.BankDataRepository;
import com.trl.userservice.repository.entity.BankDataEntity;
import com.trl.userservice.service.BankDataService;
import com.trl.userservice.service.converter.BankDataConverter;
import com.trl.userservice.utils.BankDataUtils;
import com.trl.userservice.utils.UserUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

import java.util.List;
import java.util.Optional;

/**
 * This class is designed to implementation methods of {@code BankDataService}.
 *
 * @author Tsyupryk Roman
 */
@Service
public class BankDataServiceImpl implements BankDataService {

    private static final Logger LOG = LoggerFactory.getLogger(BankDataServiceImpl.class);
    private static final String EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS = "One of parameters is illegal. Parameters must be " +
            "not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.";
    private static final String EXCEPTION_MESSAGE_BANK_DATA_BY_USER_ID_NOT_EXIST = "BankData with this userId = %s not exist.";
    private static final String EXCEPTION_MESSAGE_BANK_DATA_BY_BANK_DATA_ID_NOT_EXIST = "BankData with this bankDataId = %s not exist.";

    private final BankDataRepository bankDataRepository;
    private final BankDataUtils bankDataUtils;
    private final BankDataConverter bankDataConverter;
    private final UserUtils userUtils;

    public BankDataServiceImpl(BankDataRepository bankDataRepository, BankDataUtils bankDataUtils, BankDataConverter bankDataConverter, UserUtils userUtils) {
        this.bankDataRepository = bankDataRepository;
        this.bankDataUtils = bankDataUtils;
        this.bankDataConverter = bankDataConverter;
        this.userUtils = userUtils;
    }

    /**
     * Add the {@literal BankDataDTO} by this {@code userId}.
     *
     * @param userId   must not be equal to {@literal null}, and {@code userId} must be greater than zero.
     * @param bankData must not be equal to {@literal null}.
     * @return {@literal BankDataDTO} this bankData to be saved.
     * @throws IllegalArgumentException in case the given {@code userId} is {@literal null}
     *                                  or if {@code userId} is equal or less zero.
     *                                  And if {@code bankData} is equals to {@literal null}.
     * @throws IllegalValueException    in case if one of the fields of {@code bankData} is equals {@literal null},
     *                                  or less zero, or is empty.
     * @throws UserNotExistException    in case if user not exist by this userId.
     */
    @Override
    public BankDataDTO add(Long userId, BankDataDTO bankData) {
        BankDataDTO bankDataResult = null;

        if ((userId == null) || (userId <= 0) || (bankData == null)) {
            LOG.debug("************ add() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ add() ---> userId = " + userId + " ---> bankData = " + bankData);
        bankDataUtils.checkFieldsOfBankData(bankData);

        LOG.debug("************ add() ---> userId = " + userId);
        userUtils.checkExistsUser(userId);

        // TODO: Find information. How can these two lines of code be done better.
        Long generatedId = bankDataRepository.count() + 1;
        bankDataRepository.add(generatedId, bankData.getBankAccountNumber(), bankData.getDateOfExpiry(), bankData.getCvi(), userId);

        BankDataEntity savedBankDataFromRepository = bankDataRepository.findById(generatedId).get();

        bankDataResult = bankDataConverter.mapEntityToDTO(savedBankDataFromRepository);

        LOG.debug("************ add() ---> bankDataResult = " + bankDataResult);

        return bankDataResult;
    }

    /**
     * Retrieves the {@literal BankDataDTO} by this {@code bankDataId}.
     *
     * @param bankDataId must not be equal to {@literal null}, and {@code bankDataId} must be greater than zero.
     * @return the {@literal BankDataDTO} with the given {@code bankDataId}.
     * @throws IllegalArgumentException In case the given {@code bankDataId} is {@literal null} or if {@code bankDataId} is equal or less zero.
     * @throws DataNotFoundException    In case if {@literal BankDataDTO} not exist with this {@code bankDataId}.
     */
    @Override
    public BankDataDTO getByBankDataId(Long bankDataId) {
        BankDataDTO bankDataResult = null;

        if ((bankDataId == null) || (bankDataId <= 0)) {
            LOG.debug("************ getByBankDataId() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ getByBankDataId() ---> bankDataId = " + bankDataId);

        Optional<BankDataEntity> bankDataFromRepositoryByBankDataId = bankDataRepository.findById(bankDataId);
        LOG.debug("************ getByBankDataId() ---> " +
                "bankDataFromRepositoryByBankDataId = " + bankDataFromRepositoryByBankDataId);

        if (bankDataFromRepositoryByBankDataId.isEmpty()) {
            LOG.debug("************ getByBankDataId() ---> " +
                    format(EXCEPTION_MESSAGE_BANK_DATA_BY_BANK_DATA_ID_NOT_EXIST, bankDataId));
            throw new DataNotFoundException(
                    format(EXCEPTION_MESSAGE_BANK_DATA_BY_BANK_DATA_ID_NOT_EXIST, bankDataId));
        }

        bankDataResult = bankDataConverter.mapEntityToDTO(bankDataFromRepositoryByBankDataId.get());

        LOG.debug("************ getByBankDataId() ---> bankDataResult = " + bankDataResult);

        return bankDataResult;
    }

    /**
     * Retrieve Page of {@literal BankDataDTOs} by this {@code userId}.
     *
     * @param userId    must not be equal to {@literal null}, and {@code userId} must be greater than zero.
     * @param startPage zero-based page index, must not be negative.
     * @param pageSize  the size of the page to be returned, must be greater than 0.
     * @return the {@literal Page<BankDataDTO>} with the given {@code userId}.
     * @throws IllegalArgumentException in case the given {@code userId} is {@literal null} or if {@code userId} is equal or less zero.
     * @throws UserNotExistException    in case if bankData with this {@literal userId} not exist.
     * @throws DataNotFoundException    in case if {@literal Page<BankDataDTO>} not exist with this {@code userId}.
     */
    @Override
    public Page<BankDataDTO> getPageOfBankDataByUserId(Long userId, Integer startPage, Integer pageSize) {
        Page<BankDataDTO> bankDataPageResult = null;

        if ((userId == null) || (userId <= 0)) {
            LOG.debug("************ getPageOfBankDataByUserId() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ getPageOfBankDataByUserId() ---> userId = " + userId
                + " ---> startPage = " + startPage + " ---> pageSize = " + pageSize);

        userUtils.checkExistsUser(userId);

        Page<BankDataEntity> bankDataByUserId = bankDataRepository.getPageOfBankDataByUserId(userId, PageRequest.of(startPage, pageSize));
        LOG.debug("************ getPageOfBankDataByUserId() ---> bankDataFromRepositoryByUserId = " + bankDataByUserId);

        if (bankDataByUserId.isEmpty()) {
            LOG.debug("************ getPageOfBankDataByUserId() ---> " + format(EXCEPTION_MESSAGE_BANK_DATA_BY_USER_ID_NOT_EXIST, userId));
            throw new DataNotFoundException(format(EXCEPTION_MESSAGE_BANK_DATA_BY_USER_ID_NOT_EXIST, userId));
        }

        bankDataPageResult = bankDataConverter.mapPageEntityToPageDTO(bankDataByUserId);
        LOG.debug("************ getPageOfBankDataByUserId() ---> bankDataPageResult = " + bankDataPageResult);

        return bankDataPageResult;
    }

    /**
     * Retrieve page of sorted {@literal BankDataDTOs} by this {@code userId}.
     *
     * @param userId    must not be equal to {@literal null}, and {@code userId} must be greater than zero.
     * @param startPage zero-based page index, must not be negative.
     * @param pageSize  the size of the page to be returned, must be greater than 0.
     * @param sortOrder the value by which the sorted BankDataDTOs will be. Must not be {@literal null}.
     * @return the {@literal Page<BankDataDTO>} with the given {@code userId}.
     * @throws IllegalArgumentException in case the given {@code userId} is {@literal null} or if {@code userId} is equal or less zero.
     * @throws UserNotExistException    in case if user with this {@literal userId} not exist.
     * @throws DataNotFoundException    in case if {@literal Page<BankDataDTO>} not exist with this {@code userId}.
     */
    @Override
    public Page<BankDataDTO> getPageOfSortedBankDataByUserId(Long userId, Integer startPage, Integer pageSize, String sortOrder) {
        Page<BankDataDTO> bankDataPageResult = null;

        if ((userId == null) || (userId <= 0)) {
            LOG.debug("************ getPageOfSortedBankDataByUserId() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ getPageOfSortedBankDataByUserId() ---> userId = " + userId
                + " ---> startPage = " + startPage + " ---> pageSize = " + pageSize + " ---> sortOrder = " + sortOrder);

        userUtils.checkExistsUser(userId);

        Page<BankDataEntity> bankDataByUserId = bankDataRepository.getPageOfBankDataByUserId(userId, PageRequest.of(startPage, pageSize, Sort.by(sortOrder)));
        LOG.debug("************ getPageOfSortedBankDataByUserId() ---> bankDataFromRepositoryByUserId = " + bankDataByUserId);

        if (bankDataByUserId.isEmpty()) {
            LOG.debug("************ getPageOfSortedBankDataByUserId() ---> " + format(EXCEPTION_MESSAGE_BANK_DATA_BY_USER_ID_NOT_EXIST, userId));
            throw new DataNotFoundException(format(EXCEPTION_MESSAGE_BANK_DATA_BY_USER_ID_NOT_EXIST, userId));
        }

        bankDataPageResult = bankDataConverter.mapPageEntityToPageDTO(bankDataByUserId);
        LOG.debug("************ getPageOfSortedBankDataByUserId() ---> bankDataPageResult = " + bankDataPageResult);

        return bankDataPageResult;
    }

    /**
     * Update the {@literal BankDataDTO} by this {@code bankDataId}.
     *
     * @param bankDataId must not be {@literal null}, and {@code bankDataId} must be greater than zero.
     * @param bankData   must not be {@literal null}.
     * @return the {@literal BankDataDTO} this bankData to be updated.
     * @throws IllegalArgumentException in case the given {@code bankDataId} is {@literal null}
     *                                  or if {@code bankDataId} is equal or less zero.
     *                                  And if {@code bankData} is equals to {@literal null}.
     * @throws DataNotFoundException    in case if {@literal BankDataDTO} not exist by {@code bankDataId}.
     * @throws TheSameValueException    in case if source value field is equals to current value field.
     */
    @Override
    public BankDataDTO updateByBankDataId(Long bankDataId, BankDataDTO bankData) {
        BankDataDTO bankDataResult = null;

        if ((bankDataId == null) || (bankDataId <= 0) || (bankData == null)) {
            LOG.debug("************ updateByBankDataId() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ updateByBankDataId() ---> bankData = " + bankDataId + " ---> bankData = " + bankData);

        BankDataDTO bankDataToBeUpdated = bankDataUtils.checkExistsBankData(bankDataId);

        // TODO: Finish this method.
        bankDataResult = bankDataToBeUpdated;

        LOG.debug("************ updateByBankDataId() ---> " + "Updated bankDataResult = " + bankDataResult);

        return bankDataResult;
    }

    /**
     * Delete the {@literal BankDataDTO} with the given {@code bankDataId}.
     *
     * @param bankDataId must not be equal to {@literal null}, and {@code bankDataId} must be greater than zero.
     * @return {@literal BankDataDTO} this bankData to be deleted.
     * @throws IllegalArgumentException  In case if the given {@code bankDataId} is {@literal null}, and if {@code bankDataId} is equal or less zero.
     * @throws BankDataNotExistException If bankData not exist with the {@code bankDataId}.
     */
    @Override
    public BankDataDTO deleteByBankDataId(Long bankDataId) {
        BankDataDTO bankDataResult = null;

        if ((bankDataId == null) || (bankDataId <= 0)) {
            LOG.debug("************ deleteByBankDataId() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ deleteByBankDataId() ---> bankDataId = " + bankDataId);

        BankDataDTO bankDataToBeDeleted = bankDataUtils.checkExistsBankData(bankDataId);

        bankDataRepository.deleteById(bankDataId);

        bankDataResult = bankDataToBeDeleted;

        LOG.debug("************ deleteByBankDataId() ---> " + "Deleted bankData = " + bankDataResult);

        return bankDataResult;
    }

    /**
     * Delete all bankData by {@code userId}.
     *
     * @param userId must not be equal to {@literal null}, and {@code userId} must be greater than zero.
     * @return {@literal List<BankDataDTO>} this {@literal List<BankDataDTO>}  to be deleted.
     * @throws IllegalArgumentException  In case if the given {@code userId} is {@literal null}, and if {@code userId} is equal or less zero.
     * @throws UserNotExistException     If user not exist with the {@code userId}.
     * @throws BankDataNotExistException If bankData not exist with the {@code userId}.
     */
    @Override
    public List<BankDataDTO> deleteAllBankDataByUserId(Long userId) {
        List<BankDataDTO> bankDataResult = null;

        if ((userId == null) || (userId <= 0)) {
            LOG.debug("************ deleteAllBankDataByUserId() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ deleteAllBankDataByUserId() ---> userId = " + userId);

        userUtils.checkExistsUser(userId);

        List<BankDataDTO> bankDataToBeDeleted = bankDataUtils.checkExistsBankDataByUserId(userId);

        bankDataRepository.deleteAllBankDataByUserId(userId);

        bankDataResult = bankDataToBeDeleted;

        LOG.debug("************ deleteAllBankDataByUserId() ---> " + "Deleted bankData = " + bankDataResult);

        return bankDataResult;
    }
}