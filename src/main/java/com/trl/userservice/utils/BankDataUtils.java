package com.trl.userservice.utils;

import com.trl.userservice.controller.dto.BankDataDTO;
import com.trl.userservice.exceptions.BankDataNotExistException;
import com.trl.userservice.exceptions.IllegalValueException;
import com.trl.userservice.repository.BankDataRepository;
import com.trl.userservice.repository.entity.BankDataEntity;
import com.trl.userservice.service.converter.BankDataConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.deleteWhitespace;

import java.util.List;
import java.util.Optional;

/**
 * This class contains methods utils for {@literal BankDataDTO}.
 *
 * @author Tsyupryk Roman
 */
@Component
public class BankDataUtils {

    private static final Logger LOG = LoggerFactory.getLogger(BankDataUtils.class);

    private final BankDataRepository bankDataRepository;

    public BankDataUtils(BankDataRepository bankDataRepository) {
        this.bankDataRepository = bankDataRepository;
    }

    /**
     * Check fields of object {@literal BankDataDTO}.
     *
     * @param bankData must not be equal to {@literal null}.
     * @throws IllegalValueException If some field of {@literal BankDataDTO} contains illegal value.
     */
    public void checkFieldsOfBankData(BankDataDTO bankData) {
        String message = "Field %s, check the field that it has the 'bankData' parameter.";

        if (bankData.getBankAccountNumber() == null) {
            LOG.debug("************ add() ---> " + format(message, "'bankAccountNumber' not be equals to null"));
            throw new IllegalValueException(format(message, "'bankAccountNumber' not be equals to null"));
        } else if ((deleteWhitespace(bankData.getBankAccountNumber()).isEmpty())) {
            LOG.debug("************ add() ---> " + format(message, "'bankAccountNumber' is empty"));
            throw new IllegalValueException(format(message, "'bankAccountNumber' is empty"));
        }

        if (bankData.getDateOfExpiry() == null) {
            LOG.debug("************ add() ---> " + format(message, "'dateOfExpiry' not be equals to null"));
            throw new IllegalValueException(format(message, "'dateOfExpiry' not be equals to null"));
        }

        if (bankData.getCvi() == null) {
            LOG.debug("************ add() ---> " + format(message, "'cvi' not be equals to null"));
            throw new IllegalValueException(format(message, "'cvi' not be equals to null"));
        } else if (bankData.getCvi() <= 0) {
            LOG.debug("************ add() ---> " + format(message, "'cvi' must be greater that zero"));
            throw new IllegalValueException(format(message, "'cvi' must be greater that zero"));
        }
    }

    /**
     * Check exist {@literal BankDataDTO} by {@code bankDataId}.
     *
     * @param bankDataId must not be equal to {@literal null}, and {@code bankDataId} must be greater than zero.
     * @return the {@literal BankDataDTO}.
     * @throws BankDataNotExistException If {@literal BankDataDTO} not exist with the {@code bankDataId}.
     */
    public BankDataDTO checkExistsBankData(Long bankDataId) {

        Optional<BankDataEntity> bankDataFromRepository = bankDataRepository.findById(bankDataId);

        if (!bankDataFromRepository.isPresent()) {
            LOG.debug("************ checkExistsBankData() ---> " + "BankData with this bankDataId = " + bankDataId + " not exist.");
            throw new BankDataNotExistException("BankData with this bankDataId = " + bankDataId + " not exist.");
        }

        return new BankDataConverter().mapEntityToDTO(bankDataFromRepository.get());
    }

    /**
     * Check exist {@literal BankDataDTOs} by {@code userId}.
     *
     * @param userId must not be equal to {@literal null}, and {@code userId} must be greater than zero.
     * @return the {@literal List<BankDataDTO>}.
     * @throws BankDataNotExistException If {@literal BankDataDTOs} not exist with the {@code userId}.
     */
    public List<BankDataDTO> checkExistsBankDataByUserId(Long userId) {

        List<BankDataEntity> bankDataFromRepository = bankDataRepository.findByUserId(userId);

        if (bankDataFromRepository.isEmpty()) {
            LOG.debug("************ checkExistsBankDataByUserId() ---> BankData with this userId = " + userId + " not exist.");
            throw new BankDataNotExistException("BankData with this userId = " + userId + " not exist.");
        }

        return new BankDataConverter().mapListEntityToListDTO(bankDataFromRepository);
    }
}