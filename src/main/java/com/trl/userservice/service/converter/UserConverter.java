package com.trl.userservice.service.converter;

import com.trl.userservice.controller.dto.UserDTO;
import com.trl.userservice.repository.entity.UserEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is designed to convert {@literal UserEntity} to {@literal UserDTO} and vice versa.
 * And also, this class is designed to convert {@literal List<UserEntity>} to {@literal List<UserDTO>} and vice versa.
 * And also, this class is designed to convert {@literal Page<UserEntity>} to {@literal Page<UserDTO>}.
 *
 * @author Tsyupryk Roman
 */
@Component
public final class UserConverter {

    private static final Logger LOG = LoggerFactory.getLogger(UserConverter.class);
    private static final String EXCEPTION_MESSAGE = "Parameter is illegal, check the parameter that are passed to the method.";

    public UserConverter() {
    }

    /**
     * Convert {@literal UserEntity} to {@literal UserDTO}.
     *
     * @param entity must not be {@literal null}.
     * @return the {@literal UserDTO}.
     * @throws IllegalArgumentException in case the given {@code entity} is {@literal null}.
     */
    public UserDTO mapEntityToDTO(UserEntity entity) {
        UserDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapEntityToDTO() ---> userEntity = " + entity
                + " ---> userEntity.getClass().getSimpleName() = " + entity.getClass().getSimpleName());

        result = new UserDTO();
        result.setUserId(entity.getId());
        result.setFirstName(entity.getFirstName());
        result.setLastName(entity.getLastName());
        result.setUserName(entity.getUserName());
        result.setEmail(entity.getEmail());
        result.setPassword(entity.getPassword());
        result.setBankData(new BankDataConverter().mapListEntityToListDTO(entity.getBankData()));
        result.setAddress(new AddressConverter().mapListEntityToListDTO(entity.getAddress()));
        result.setBirthday(entity.getBirthday());

        LOG.debug("************ mapEntityToDTO() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * Convert {@literal List<UserEntity>} to {@literal List<UserDTO>}.
     *
     * @param entities must not be {@literal null}.
     * @return the {@literal List<UserDTO>}.
     * @throws IllegalArgumentException in case the given {@code entities} is {@literal null}.
     */
    public List<UserDTO> mapListEntityToListDTO(List<UserEntity> entities) {
        List<UserDTO> resultList = null;

        if (entities == null) {
            LOG.debug("************ mapListEntityToListDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListEntityToListDTO() ---> userEntityList = " + entities);

        resultList = entities.parallelStream()
                .map(userEntity -> new UserConverter().mapEntityToDTO(userEntity))
                .collect(Collectors.toList());

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * Convert {@literal Page<UserEntity>} to {@literal Page<UserDTO>}.
     *
     * @param entities must not be {@literal null}.
     * @return the {@literal Page<UserDTO>}.
     * @throws IllegalArgumentException in case the given {@code entities} is {@literal null}.
     */
    public Page<UserDTO> mapPageEntityToPageDTO(Page<UserEntity> entities) {
        Page<UserDTO> resultPage;

        if (entities == null) {
            LOG.debug("************ mapPageEntityToPageDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapPageEntityToPageDTO() ---> userEntityPage = " + entities);

        resultPage = entities.map(userEntity -> new UserConverter().mapEntityToDTO(userEntity));

        LOG.debug("************ mapPageEntityToPageDTO() ---> resultPage = " + resultPage);

        return resultPage;
    }

    /**
     * Convert {@literal UserDTO} to {@literal UserEntity}.
     *
     * @param dto must not be {@literal null}.
     * @return the {@literal UserEntity}.
     * @throws IllegalArgumentException in case the given {@code dto} is {@literal null}.
     */
    public UserEntity mapDTOToEntity(UserDTO dto) {
        UserEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapDTOToEntity() ---> userDTO = " + dto
                + " ---> userDTO.getClass().getSimpleName() = " + dto.getClass().getSimpleName());

        result = new UserEntity();
        result.setId(dto.getUserId());
        result.setFirstName(dto.getFirstName());
        result.setLastName(dto.getLastName());
        result.setUserName(dto.getUserName());
        result.setEmail(dto.getEmail());
        result.setPassword(dto.getPassword());
        result.setBankData(new BankDataConverter().mapListDTOToListEntity(dto.getBankData()));
        result.setAddress(new AddressConverter().mapListDTOToListEntity(dto.getAddress()));
        result.setBirthday(dto.getBirthday());

        LOG.debug("************ mapDTOToEntity() ---> result = "
                + result + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * Convert {@literal List<UserDTO>} to {@literal List<UserEntity>}.
     *
     * @param dtos must not be {@literal null}.
     * @return the {@literal List<UserEntity>}.
     * @throws IllegalArgumentException in case the given {@code dtos} is {@literal null}.
     */
    public List<UserEntity> mapListDTOToListEntity(List<UserDTO> dtos) {
        List<UserEntity> resultList = null;

        if (dtos == null) {
            LOG.debug("************ mapListDTOToListEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListDTOToListEntity() ---> userDTOList = " + dtos);

        resultList = dtos.parallelStream()
                .map(userDTO -> new UserConverter().mapDTOToEntity(userDTO))
                .collect(Collectors.toList());

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}