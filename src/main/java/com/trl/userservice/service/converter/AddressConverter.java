package com.trl.userservice.service.converter;

import com.trl.userservice.controller.dto.AddressDTO;
import com.trl.userservice.repository.entity.AddressEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is designed to convert {@literal AddressEntity} to {@literal AddressDTO} and vice versa.
 * And also, this class is designed to convert {@literal List<BookEntity>} to {@literal List<BookDTO>} and vice versa.
 * And also, this class is designed to convert {@literal Page<AddressEntity>} to {@literal Page<AddressDTO>}.
 *
 * @author Tsyupryk Roman
 */
public final class AddressConverter {

    private static final Logger LOG = LoggerFactory.getLogger(AddressConverter.class);
    private static final String EXCEPTION_MESSAGE = "Parameter is illegal, check the parameter that are passed to the method.";

    private AddressConverter() {
    }

    /**
     * Convert {@literal AddressEntity} to {@literal AddressDTO}.
     *
     * @param entity must not be {@literal null}.
     * @return the {@literal AddressDTO}.
     * @throws IllegalArgumentException in case the given {@code entity} is {@literal null}.
     */
    public static AddressDTO mapEntityToDTO(AddressEntity entity) {
        AddressDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapEntityToDTO() ---> bookEntity = " + entity
                + " ---> bookEntity.getClass().getSimpleName() = " + entity.getClass().getSimpleName());

        result = new AddressDTO();
        result.setAddressId(entity.getId());
        result.setCountry(entity.getCountry());
        result.setCity(entity.getCity());
        result.setStreet(entity.getStreet());
        result.setHouseNumber(entity.getHouseNumber());
        result.setPostcode(entity.getPostcode());

        LOG.debug("************ mapEntityToDTO() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * Convert {@literal List<AddressEntity>} to {@literal List<AddressDTO>}.
     *
     * @param entities must not be {@literal null}.
     * @return the {@literal List<AddressDTO>}.
     * @throws IllegalArgumentException in case the given {@code entities} is {@literal null}.
     */
    public static List<AddressDTO> mapListEntityToListDTO(List<AddressEntity> entities) {
        List<AddressDTO> resultList = null;

        if (entities == null) {
            LOG.debug("************ mapListEntityToListDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListEntityToListDTO() ---> addressEntityList = " + entities);

        resultList = entities.parallelStream()
                .map(AddressConverter::mapEntityToDTO)
                .collect(Collectors.toList());

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * Convert {@literal Page<AddressEntity>} to {@literal Page<AddressDTO>}.
     *
     * @param entities must not be {@literal null}.
     * @return the {@literal Page<AddressDTO>}.
     * @throws IllegalArgumentException in case the given {@code entities} is {@literal null}.
     */
    public static Page<AddressDTO> mapPageEntityToPageDTO(Page<AddressEntity> entities) {
        Page<AddressDTO> resultPage;

        if (entities == null) {
            LOG.debug("************ mapPageEntityToPageDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapPageEntityToPageDTO() ---> addressEntityPage = " + entities);

        resultPage = entities.map(AddressConverter::mapEntityToDTO);

        LOG.debug("************ mapPageEntityToPageDTO() ---> resultList = " + resultPage);

        return resultPage;
    }

    /**
     * Convert {@literal AddressDTO} to {@literal AddressEntity}.
     *
     * @param dto must not be {@literal null}.
     * @return the {@literal AddressEntity}.
     * @throws IllegalArgumentException in case the given {@code dto} is {@literal null}.
     */
    public static AddressEntity mapDTOToEntity(AddressDTO dto) {
        AddressEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapDTOToEntity() ---> addressDTO = " + dto
                + " ---> addressDTO.getClass().getSimpleName() = " + dto.getClass().getSimpleName());

        result = new AddressEntity();
        result.setId(dto.getAddressId());
        result.setCountry(dto.getCountry());
        result.setCity(dto.getCity());
        result.setStreet(dto.getStreet());
        result.setHouseNumber(dto.getHouseNumber());
        result.setPostcode(dto.getPostcode());

        LOG.debug("************ mapDTOToEntity() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * Convert {@literal List<AddressDTO} to {@literal List<AddressEntity>}.
     *
     * @param dtos must not be {@literal null}.
     * @return the {@literal List<AddressEntity>}.
     * @throws IllegalArgumentException in case the given {@code dtos} is {@literal null}.
     */
    public static List<AddressEntity> mapListDTOToListEntity(List<AddressDTO> dtos) {
        List<AddressEntity> resultList = null;

        if (dtos == null) {
            LOG.debug("************ mapListDTOToListEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListDTOToListEntity() ---> addressDTOList = " + dtos);

        resultList = dtos.parallelStream()
                .map(AddressConverter::mapDTOToEntity)
                .collect(Collectors.toList());

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}