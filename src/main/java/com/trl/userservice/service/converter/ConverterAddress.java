package com.trl.userservice.service.converter;

import com.trl.userservice.controller.dto.AddressDTO;
import com.trl.userservice.repository.entity.AddressEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 *
 */
public final class ConverterAddress {

    private static final Logger LOG = LoggerFactory.getLogger(ConverterAddress.class);

    private ConverterAddress() {
    }

    /**
     * @param addressEntity
     * @return
     */
    public static AddressDTO mapEntityToDTO(AddressEntity addressEntity) {
        AddressDTO result = null;

        LOG.debug("************ mapEntityToDTO() ---> addressEntity = " + addressEntity + " ---> addressEntity.getClass().getSimpleName() = " + (addressEntity != null ? addressEntity.getClass().getSimpleName() : "null"));

        if (addressEntity != null) {
            result = new AddressDTO(
                    addressEntity.getId(),
                    addressEntity.getCountry(),
                    addressEntity.getCity(),
                    addressEntity.getStreet(),
                    addressEntity.getHouseNumber(),
                    addressEntity.getPostcode(),
                    ConverterUser.mapEntityToDTO(addressEntity.getUser())
            );
        }

        LOG.debug("************ mapEntityToDTO() ---> result = " + result + " ---> result.getClass().getSimpleName() = " + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param addressEntitySet
     * @return
     */
    public static Set<AddressDTO> mapSetEntityToSetDTO(Set<AddressEntity> addressEntitySet) {
        Set<AddressDTO> resultSet = null;

        LOG.debug("************ mapSetEntityToSetDTO() ---> addressEntitySet = " + addressEntitySet);

        if (addressEntitySet != null) {

            resultSet = addressEntitySet.stream()
                    .map(ConverterAddress::mapEntityToDTO)
                    .collect(Collectors.toCollection(
                            () -> new TreeSet<>(Comparator.comparingLong(AddressDTO::getId))));
        }

        LOG.debug("************ mapSetEntityToSetDTO() ---> resultSet = " + resultSet);

        return resultSet;
    }

    /**
     * @param addressDTO
     * @return
     */
    public static AddressEntity mapDTOToEntity(AddressDTO addressDTO) {
        AddressEntity result = null;

        LOG.debug("************ mapDTOToEntity() ---> addressDTO = " + addressDTO + " ---> addressDTO.getClass().getSimpleName() = " + (addressDTO != null ? addressDTO.getClass().getSimpleName() : "null"));

        if (addressDTO != null) {
            result = new AddressEntity(
                    addressDTO.getId(),
                    addressDTO.getCountry(),
                    addressDTO.getCity(),
                    addressDTO.getStreet(),
                    addressDTO.getHouseNumber(),
                    addressDTO.getPostcode(),
                    ConverterUser.mapDTOToEntity(addressDTO.getUser())
            );
        }

        LOG.debug("************ mapDTOToEntity() ---> result = " + result + " ---> result.getClass().getSimpleName() = " + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param addressDTOSet
     * @return
     */
    public static Set<AddressEntity> mapSetDTOToSetEntity(Set<AddressDTO> addressDTOSet) {
        Set<AddressEntity> resultSet = null;

        LOG.debug("************ mapSetDTOToSetEntity() ---> addressDTOSet = " + addressDTOSet);

        if (addressDTOSet != null) {
            resultSet = addressDTOSet.stream()
                    .map(ConverterAddress::mapDTOToEntity)
                    .collect(Collectors.toCollection(
                            () -> new TreeSet<>(Comparator.comparingLong(AddressEntity::getId))));
        }

        LOG.debug("************ mapSetDTOToSetEntity() ---> resultSet = " + resultSet);

        return resultSet;
    }

}
