package com.trl.userservice.service.converter;

import com.trl.userservice.controller.dto.BankDataDTO;
import com.trl.userservice.repository.entity.BankDataEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is designed to convert {@literal BankDataEntity} to {@literal BankDataDTO} and vice versa.
 * And also, this class is designed to convert {@literal List<BankDataEntity>} to {@literal List<BankDataDTO>} and vice versa.
 * And also, this class is designed to convert {@literal Page<BankDataEntity>} to {@literal Page<BankDataDTO>}.
 *
 * @author Tsyupryk Roman
 */
public final class BankDataConverter {

    private static final Logger LOG = LoggerFactory.getLogger(BankDataConverter.class);
    private static final String EXCEPTION_MESSAGE = "Parameter is illegal, check the parameter that are passed to the method.";

    private BankDataConverter() {
    }

    /**
     * Convert {@literal BankDataEntity} to {@literal BankDataDTO}.
     *
     * @param entity must not be {@literal null}.
     * @return the {@literal BankDataDTO}.
     * @throws IllegalArgumentException in case the given {@code entity} is {@literal null}.
     */
    public static BankDataDTO mapEntityToDTO(BankDataEntity entity) {
        BankDataDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapEntityToDTO() ---> bankDataEntity = " + entity
                + " ---> bankDataEntity.getClass().getSimpleName() = " + entity.getClass().getSimpleName());

        result = new BankDataDTO();
        result.setBankDataId(entity.getId());
        result.setBankAccountNumber(entity.getBankAccountNumber());
        result.setDateOfExpiry(entity.getDateOfExpiry());
        result.setCvi(entity.getCvi());

        LOG.debug("************ mapEntityToDTO() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * Convert {@literal List<BankDataEntity>} to {@literal List<BankDataDTO>}.
     *
     * @param entities must not be {@literal null}.
     * @return the {@literal List<BankDataDTO>}.
     * @throws IllegalArgumentException in case the given {@code entities} is {@literal null}.
     */
    public static List<BankDataDTO> mapListEntityToListDTO(List<BankDataEntity> entities) {
        List<BankDataDTO> resultList = null;

        if (entities == null) {
            LOG.debug("************ mapListEntityToListDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListEntityToListDTO() ---> bankDataEntityList = " + entities);

        resultList = entities.parallelStream()
                .map(BankDataConverter::mapEntityToDTO)
                .collect(Collectors.toList());

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * Convert {@literal Page<BankDataEntity>} to {@literal Page<BankDataDTO>}.
     *
     * @param entities must not be {@literal null}.
     * @return the {@literal Page<BankDataDTO>}.
     * @throws IllegalArgumentException in case the given {@code entities} is {@literal null}.
     */
    public static Page<BankDataDTO> mapPageEntityToPageDTO(Page<BankDataEntity> entities) {
        Page<BankDataDTO> resultPage;

        if (entities == null) {
            LOG.debug("************ mapPageEntityToPageDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapPageEntityToPageDTO() ---> BankDataEntityPage = " + entities);

        resultPage = entities.map(BankDataConverter::mapEntityToDTO);

        LOG.debug("************ mapPageEntityToPageDTO() ---> resultList = " + resultPage);

        return resultPage;
    }

    /**
     * Convert {@literal BankDataDTO} to {@literal BankDataEntity}.
     *
     * @param dto must not be {@literal null}.
     * @return the {@literal BankDataEntity}.
     * @throws IllegalArgumentException in case the given {@code dto} is {@literal null}.
     */
    public static BankDataEntity mapDTOToEntity(BankDataDTO dto) {
        BankDataEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapDTOToEntity() ---> bankDataDTO = " + dto
                + " ---> bankDataDTO.getClass().getSimpleName() = " + dto.getClass().getSimpleName());

        result = new BankDataEntity();
        result.setId(dto.getBankDataId());
        result.setBankAccountNumber(dto.getBankAccountNumber());
        result.setDateOfExpiry(dto.getDateOfExpiry());
        result.setCvi(dto.getCvi());

        LOG.debug("************ mapDTOToEntity() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * Convert {@literal List<BankDataDTO} to {@literal List<BankDataEntity>}.
     *
     * @param dtos must not be {@literal null}.
     * @return the {@literal List<BankDataEntity>}.
     * @throws IllegalArgumentException in case the given {@code dtos} is {@literal null}.
     */
    public static List<BankDataEntity> mapListDTOToListEntity(List<BankDataDTO> dtos) {
        List<BankDataEntity> resultList;

        if (dtos == null) {
            LOG.debug("************ mapListDTOToListEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListDTOToListEntity() ---> bankDataDTOList = " + dtos);

        resultList = dtos.parallelStream()
                .map(BankDataConverter::mapDTOToEntity)
                .collect(Collectors.toList());

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}