package com.trl.userservice.service.converter;

import com.trl.userservice.controller.dto.UserDTO;
import com.trl.userservice.repository.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 *
 */
public final class ConverterUser {

    private static final Logger LOG = LoggerFactory.getLogger(ConverterUser.class);

    private ConverterUser() {
    }

    /**
     * @param userEntity
     * @return
     */
    public static UserDTO mapEntityToDTO(UserEntity userEntity) {
        UserDTO result = null;

        LOG.debug("************ mapEntityToDTO() ---> userEntity = " + userEntity + " ---> userEntity.getClass().getSimpleName() = " + (userEntity != null ? userEntity.getClass().getSimpleName() : "null"));

        if (userEntity != null) {
            result = new UserDTO(
                    userEntity.getId(),
                    userEntity.getFirstName(),
                    userEntity.getLastName(),
                    userEntity.getEmail(),
                    userEntity.getPassword(),
                    ConverterBankData.mapSetEntityToSetDTO(userEntity.getBankData()),
                    ConverterAddress.mapSetEntityToSetDTO(userEntity.getAddress()),
                    userEntity.getBirthday()
            );
        }

        LOG.debug("************ mapEntityToDTO() ---> result = " + result + " ---> result.getClass().getSimpleName() = " + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param userEntitySet
     * @return
     */
    public static Set<UserDTO> mapSetEntityToSetDTO(Set<UserEntity> userEntitySet) {
        Set<UserDTO> resultSet = null;

        LOG.debug("************ mapSetEntityToSetDTO() ---> userEntitySet = " + userEntitySet);

        if (userEntitySet != null) {
            resultSet = userEntitySet.parallelStream()
                    .map(ConverterUser::mapEntityToDTO)
                    .collect(Collectors.toCollection(
                            () -> new TreeSet<>(Comparator.comparingLong(UserDTO::getId))));
        }

        LOG.debug("************ mapSetEntityToSetDTO() ---> resultSet = " + resultSet);

        return resultSet;
    }

    /**
     * @param userDTO
     * @return
     */
    public static UserEntity mapDTOToEntity(UserDTO userDTO) {
        UserEntity result = null;

        LOG.debug("************ mapDTOToEntity() ---> userDTO = " + userDTO + " ---> userDTO.getClass().getSimpleName() = " + (userDTO != null ? userDTO.getClass().getSimpleName() : "null"));

        if (userDTO != null) {
            result = new UserEntity(
                    userDTO.getId(),
                    userDTO.getFirstName(),
                    userDTO.getLastName(),
                    userDTO.getEmail(),
                    userDTO.getPassword(),
                    ConverterBankData.mapSetDTOToSetEntity(userDTO.getBankData()),
                    ConverterAddress.mapSetDTOToSetEntity(userDTO.getAddress()),
                    userDTO.getBirthday()
            );
        }

        LOG.debug("************ mapDTOToEntity() ---> result = " + result + " ---> result.getClass().getSimpleName() = " + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param userDTOSet
     * @return
     */
    public static Set<UserEntity> mapSetDTOToSetEntity(Set<UserDTO> userDTOSet) {
        Set<UserEntity> resultSet = null;

        LOG.debug("************ mapSetDTOToSetEntity() ---> userDTOSet = " + userDTOSet);

        if (userDTOSet != null) {
            resultSet = userDTOSet.stream()
                    .map(ConverterUser::mapDTOToEntity)
                    .collect(Collectors.toCollection(
                            () -> new TreeSet<>(Comparator.comparingLong(UserEntity::getId))));
        }

        LOG.debug("************ mapSetDTOToSetEntity() ---> resultSet = " + resultSet);

        return resultSet;
    }

}
