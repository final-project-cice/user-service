package com.trl.user.service.converter;

import com.trl.user.controller.dto.BankDataDTO;
import com.trl.user.repository.entity.BankDataEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 *
 */
public final class ConverterBankData {

    private static final Logger LOG = LoggerFactory.getLogger(ConverterBankData.class);

    private ConverterBankData() {
    }

    /**
     * @param bankDataEntity
     * @return
     */
    public static BankDataDTO mapEntityToDTO(BankDataEntity bankDataEntity) {
        BankDataDTO result = null;

        LOG.debug("************ mapEntityToDTO() ---> bankDataEntity = " + bankDataEntity + " ---> bankDataEntity.getClass().getSimpleName() = " + (bankDataEntity != null ? bankDataEntity.getClass().getSimpleName() : "null"));

        if (bankDataEntity != null) {
            result = new BankDataDTO(
                    bankDataEntity.getId(),
                    bankDataEntity.getBankAccountNumber(),
                    bankDataEntity.getDateOfExpiry(),
                    bankDataEntity.getCvi(),
                    ConverterUser.mapEntityToDTO(bankDataEntity.getUser())
            );
        }

        LOG.debug("************ mapEntityToDTO() ---> result = " + result + " ---> result.getClass().getSimpleName() = " + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param bankDataEntitySet
     * @return
     */
    public static Set<BankDataDTO> mapSetEntityToSetDTO(Set<BankDataEntity> bankDataEntitySet) {
        Set<BankDataDTO> resultSet = null;

        LOG.debug("************ mapSetEntityToSetDTO() ---> bankDataEntitySet = " + bankDataEntitySet);

        if (bankDataEntitySet != null) {
            resultSet = bankDataEntitySet.stream()
                    .map(ConverterBankData::mapEntityToDTO)
                    .collect(Collectors.toCollection(
                            () -> new TreeSet<>(Comparator.comparingLong(BankDataDTO::getId))));
        }

        LOG.debug("************ mapSetEntityToSetDTO() ---> resultSet = " + resultSet);

        return resultSet;
    }

    /**
     * @param bankDataDTO
     * @return
     */
    public static BankDataEntity mapDTOToEntity(BankDataDTO bankDataDTO) {
        BankDataEntity result = null;

        LOG.debug("************ mapDTOToEntity() ---> bankDataDTO = " + bankDataDTO + " ---> bankDataDTO.getClass().getSimpleName() = " + (bankDataDTO != null ? bankDataDTO.getClass().getSimpleName() : "null"));

        if (bankDataDTO != null) {
            result = new BankDataEntity(
                    bankDataDTO.getId(),
                    bankDataDTO.getBankAccountNumber(),
                    bankDataDTO.getDateOfExpiry(),
                    bankDataDTO.getCvi(),
                    ConverterUser.mapDTOToEntity(bankDataDTO.getUser())
            );
        }

        LOG.debug("************ mapDTOToEntity() ---> result = " + result + " ---> result.getClass().getSimpleName() = " + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param bankDataDTOSet
     * @return
     */
    public static Set<BankDataEntity> mapSetDTOToSetEntity(Set<BankDataDTO> bankDataDTOSet) {
        Set<BankDataEntity> resultSet = null;

        LOG.debug("************ mapSetDTOToSetEntity() ---> bankDataDTOSet = " + bankDataDTOSet);

        if (bankDataDTOSet != null) {
            resultSet = bankDataDTOSet.stream()
                    .map(ConverterBankData::mapDTOToEntity)
                    .collect(Collectors.toCollection(
                            () -> new TreeSet<>(Comparator.comparingLong(BankDataEntity::getId))));
        }

        LOG.debug("************ mapSetDTOToSetEntity() ---> resultSet = " + resultSet);

        return resultSet;
    }

}
