package com.trl.user.service.converter;

import com.trl.user.controller.dto.UserDTO;
import com.trl.user.repository.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 *
 */
@Slf4j
public final class ConverterUser {

    private ConverterUser() {
    }

    /**
     * @param userEntity
     * @return
     */
    public static UserDTO mapEntityToDTO(UserEntity userEntity) {
        UserDTO result = null;

        log.debug("************ mapEntityToDTO() ---> userEntity = " + userEntity + " ---> userEntity.getClass().getSimpleName() = " + (userEntity != null ? userEntity.getClass().getSimpleName() : "null"));

        if (userEntity != null) {
            result = new UserDTO()
                    .setId(userEntity.getId())
                    .setFirstName(userEntity.getFirstName())
                    .setLastName(userEntity.getLastName())
                    .setEmail(userEntity.getEmail())
                    .setPassword(userEntity.getPassword())
                    .setBankData(ConverterBankData.mapSetEntityToSetDTO(userEntity.getBankData()))
                    .setAddress(ConverterAddress.mapSetEntityToSetDTO(userEntity.getAddress()))
                    .setBirthday(userEntity.getBirthday());
        }

        log.debug("************ mapEntityToDTO() ---> result = " + result + " ---> result.getClass().getSimpleName() = " + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param userEntitySet
     * @return
     */
    public static Set<UserDTO> mapSetEntityToSetDTO(Set<UserEntity> userEntitySet) {
        Set<UserDTO> resultSet = null;

        log.debug("************ mapSetEntityToSetDTO() ---> userEntitySet = " + userEntitySet);

        if (userEntitySet != null) {
            resultSet = userEntitySet.parallelStream()
                    .map(ConverterUser::mapEntityToDTO)
                    .collect(Collectors.toCollection(
                            () -> new TreeSet<>(Comparator.comparingLong(UserDTO::getId))));
        }

        log.debug("************ mapSetEntityToSetDTO() ---> resultSet = " + resultSet);

        return resultSet;
    }

    /**
     * @param userDTO
     * @return
     */
    public static UserEntity mapDTOToEntity(UserDTO userDTO) {
        UserEntity result = null;

        log.debug("************ mapDTOToEntity() ---> userDTO = " + userDTO + " ---> userDTO.getClass().getSimpleName() = " + (userDTO != null ? userDTO.getClass().getSimpleName() : "null"));

        if (userDTO != null) {
            result = new UserEntity()
                    .setId(userDTO.getId())
                    .setFirstName(userDTO.getFirstName())
                    .setLastName(userDTO.getLastName())
                    .setEmail(userDTO.getEmail())
                    .setPassword(userDTO.getPassword())
                    .setBankData(ConverterBankData.mapSetDTOToSetEntity(userDTO.getBankData()))
                    .setAddress(ConverterAddress.mapSetDTOToSetEntity(userDTO.getAddress()))
                    .setBirthday(userDTO.getBirthday());
        }

        log.debug("************ mapDTOToEntity() ---> result = " + result + " ---> result.getClass().getSimpleName() = " + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param userDTOSet
     * @return
     */
    public static Set<UserEntity> mapSetDTOToSetEntity(Set<UserDTO> userDTOSet) {
        Set<UserEntity> resultSet = null;

        log.debug("************ mapSetDTOToSetEntity() ---> userDTOSet = " + userDTOSet);

        if (userDTOSet != null) {
            resultSet = userDTOSet.stream()
                    .map(ConverterUser::mapDTOToEntity)
                    .collect(Collectors.toCollection(
                            () -> new TreeSet<>(Comparator.comparingLong(UserEntity::getId))));
        }

        log.debug("************ mapSetDTOToSetEntity() ---> resultSet = " + resultSet);

        return resultSet;
    }

}
