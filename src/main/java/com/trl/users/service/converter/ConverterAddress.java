package com.trl.users.service.converter;

import com.trl.users.controller.dto.AddressDTO;
import com.trl.users.repository.entity.AddressEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 */
@Slf4j
public class ConverterAddress {

    private ConverterAddress() {
    }

    /**
     * @param addressEntity
     * @return
     */
    public static AddressDTO mapEntityToDTO(AddressEntity addressEntity) {
        AddressDTO result = null;

        log.debug("************ mapEntityToDTO() ---> addressEntity = " + addressEntity + " ---> addressEntity.getClass().getSimpleName() = " + (addressEntity != null ? addressEntity.getClass().getSimpleName() : "null"));

        if (addressEntity != null) {
            result = new AddressDTO()
                    .setId(addressEntity.getId())
                    .setCountry(addressEntity.getCountry())
                    .setCity(addressEntity.getCity())
                    .setStreet(addressEntity.getStreet())
                    .setHouseNumber(addressEntity.getHouseNumber())
                    .setPostcode(addressEntity.getPostcode())
                    .setUser(ConverterUser.mapEntityToDTO(addressEntity.getUser()));
        }

        log.debug("************ mapEntityToDTO() ---> result = " + result + " ---> result.getClass().getSimpleName() = " + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param addressEntitySet
     * @return
     */
    public static Set<AddressDTO> mapSetEntityToSetDTO(Set<AddressEntity> addressEntitySet) {
        Set<AddressDTO> resultSet = null;

        log.debug("************ mapSetEntityToSetDTO() ---> addressEntitySet = " + addressEntitySet);

        if (addressEntitySet != null) {
            resultSet = new TreeSet<>(Comparator.comparing(AddressDTO::getId));
            for (AddressEntity entity : addressEntitySet) {
                resultSet.add(mapEntityToDTO(entity));
            }
        }

        log.debug("************ mapSetEntityToSetDTO() ---> resultSet = " + resultSet);

        return resultSet;
    }

    /**
     * @param addressDTO
     * @return
     */
    public static AddressEntity mapDTOToEntity(AddressDTO addressDTO) {
        AddressEntity result = null;

        log.debug("************ mapDTOToEntity() ---> addressDTO = " + addressDTO + " ---> addressDTO.getClass().getSimpleName() = " + (addressDTO != null ? addressDTO.getClass().getSimpleName() : "null"));

        if (addressDTO != null) {
            result = new AddressEntity()
                    .setId(addressDTO.getId())
                    .setCountry(addressDTO.getCountry())
                    .setCity(addressDTO.getCity())
                    .setStreet(addressDTO.getStreet())
                    .setHouseNumber(addressDTO.getHouseNumber())
                    .setPostcode(addressDTO.getPostcode())
                    .setUser(ConverterUser.mapDTOToEntity(addressDTO.getUser()));
        }

        log.debug("************ mapDTOToEntity() ---> result = " + result + " ---> result.getClass().getSimpleName() = " + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param addressDTOSet
     * @return
     */
    public static Set<AddressEntity> mapSetDTOToSetEntity(Set<AddressDTO> addressDTOSet) {
        Set<AddressEntity> resultSet = null;

        log.debug("************ mapSetDTOToSetEntity() ---> addressDTOSet = " + addressDTOSet);

        if (addressDTOSet != null) {
            resultSet = new TreeSet<>(Comparator.comparing(AddressEntity::getId));
            for (AddressDTO dto : addressDTOSet) {
                resultSet.add(mapDTOToEntity(dto));
            }
        }


        log.debug("************ mapSetDTOToSetEntity() ---> resultSet = " + resultSet);

        return resultSet;
    }

}
