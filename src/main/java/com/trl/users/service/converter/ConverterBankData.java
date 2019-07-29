package com.trl.users.service.converter;

import com.trl.users.controller.dto.BankDataDTO;
import com.trl.users.repository.entity.BankDataEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 *
 */
@Slf4j
public final class ConverterBankData {

    private ConverterBankData() {
    }

    /**
     * @param bankDataEntity
     * @return
     */
    public static BankDataDTO mapEntityToDTO(BankDataEntity bankDataEntity) {
        BankDataDTO result = null;

        log.debug("************ mapEntityToDTO() ---> bankDataEntity = " + bankDataEntity + " ---> bankDataEntity.getClass().getSimpleName() = " + (bankDataEntity != null ? bankDataEntity.getClass().getSimpleName() : "null"));

        if (bankDataEntity != null) {
            result = new BankDataDTO()
                    .setId(bankDataEntity.getId())
                    .setBankAccountNumber(bankDataEntity.getBankAccountNumber())
                    .setDateOfExpiry(bankDataEntity.getDateOfExpiry())
                    .setCvi(bankDataEntity.getCvi())
                    .setUser(ConverterUser.mapEntityToDTO(bankDataEntity.getUser()));
        }

        log.debug("************ mapEntityToDTO() ---> result = " + result + " ---> result.getClass().getSimpleName() = " + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param bankDataEntitySet
     * @return
     */
    public static Set<BankDataDTO> mapSetEntityToSetDTO(Set<BankDataEntity> bankDataEntitySet) {
        Set<BankDataDTO> resultSet = null;

        log.debug("************ mapSetEntityToSetDTO() ---> bankDataEntitySet = " + bankDataEntitySet);

        if (bankDataEntitySet != null) {
            resultSet = bankDataEntitySet.stream()
                    .map(ConverterBankData::mapEntityToDTO)
                    .collect(Collectors.toCollection(
                            () -> new TreeSet<>(Comparator.comparingLong(BankDataDTO::getId))));
        }

        log.debug("************ mapSetEntityToSetDTO() ---> resultSet = " + resultSet);

        return resultSet;
    }

    /**
     * @param bankDataDTO
     * @return
     */
    public static BankDataEntity mapDTOToEntity(BankDataDTO bankDataDTO) {
        BankDataEntity result = null;

        log.debug("************ mapDTOToEntity() ---> bankDataDTO = " + bankDataDTO + " ---> bankDataDTO.getClass().getSimpleName() = " + (bankDataDTO != null ? bankDataDTO.getClass().getSimpleName() : "null"));

        if (bankDataDTO != null) {
            result = new BankDataEntity()
                    .setId(bankDataDTO.getId())
                    .setBankAccountNumber(bankDataDTO.getBankAccountNumber())
                    .setDateOfExpiry(bankDataDTO.getDateOfExpiry())
                    .setCvi(bankDataDTO.getCvi())
                    .setUser(ConverterUser.mapDTOToEntity(bankDataDTO.getUser()));
        }

        log.debug("************ mapDTOToEntity() ---> result = " + result + " ---> result.getClass().getSimpleName() = " + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param bankDataDTOSet
     * @return
     */
    public static Set<BankDataEntity> mapSetDTOToSetEntity(Set<BankDataDTO> bankDataDTOSet) {
        Set<BankDataEntity> resultSet = null;

        log.debug("************ mapSetDTOToSetEntity() ---> bankDataDTOSet = " + bankDataDTOSet);

        if (bankDataDTOSet != null) {
            resultSet = bankDataDTOSet.stream()
                    .map(ConverterBankData::mapDTOToEntity)
                    .collect(Collectors.toCollection(
                            () -> new TreeSet<>(Comparator.comparingLong(BankDataEntity::getId))));
        }

        log.debug("************ mapSetDTOToSetEntity() ---> resultSet = " + resultSet);

        return resultSet;
    }

}
