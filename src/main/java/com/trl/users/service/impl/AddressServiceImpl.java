package com.trl.users.service.impl;

import com.trl.users.controller.dto.AddressDTO;
import com.trl.users.controller.dto.UserDTO;
import com.trl.users.exceptions.ExceptionUserNotHaveAddress;
import com.trl.users.repository.AddressRepository;
import com.trl.users.repository.entity.AddressEntity;
import com.trl.users.repository.entity.UserEntity;
import com.trl.users.service.AddressService;
import com.trl.users.service.converter.ConverterUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 *
 */
@AllArgsConstructor
@Slf4j
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    /**
     * @param addressDTO
     * @return
     */
    @Override
    public AddressDTO create(AddressDTO addressDTO) {

        // TODO: Terminar este metodo.

        return null;
    }

    /**
     * @param id
     * @param country
     * @return
     */
    @Override
    public AddressDTO updateCountry(Long id, String country) {

        // TODO: Terminar este metodo.

        return null;
    }

    /**
     * @param id
     * @param city
     * @return
     */
    @Override
    public AddressDTO updateCity(Long id, String city) {

        // TODO: Terminar este metodo.

        return null;
    }

    /**
     * @param id
     * @param street
     * @return
     */
    @Override
    public AddressDTO updateStreet(Long id, String street) {

        // TODO: Terminar este metodo.

        return null;
    }

    /**
     * @param id
     * @param houseNumber
     * @return
     */
    @Override
    public AddressDTO updateHouseNumber(Long id, String houseNumber) {

        // TODO: Terminar este metodo.

        return null;
    }

    /**
     * @param id
     * @param postCode
     * @return
     */
    @Override
    public AddressDTO updatePostCode(Long id, String postCode) {

        // TODO: Terminar este metodo.

        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Boolean deleteById(Long id) {

        // TODO: Terminar este metodo.

        return null;
    }

    /**
     * @param userDTO
     * @return
     */
    @Transactional
    @Override
    public Boolean deleteByUser(UserDTO userDTO) throws ExceptionUserNotHaveAddress {

        boolean isDeletedAddress = false;

        log.debug("************ deleteByUser() ---> userDTO = " + userDTO);

        UserEntity userEntity = ConverterUser.mapDTOToEntity(userDTO);

        List<AddressEntity> addressFromRepositoryByUser = addressRepository.findByUser(userEntity);

        log.debug("************ deleteByUser() ---> addressFromRepositoryById = " + addressFromRepositoryByUser);

        if (!addressFromRepositoryByUser.isEmpty()) {
            addressRepository.deleteByUser(userEntity);
            isDeletedAddress = true;
        } else {
            log.debug("************ deleteByUser() ---> user with this id = '" + userDTO + "' not exist.");
            throw new ExceptionUserNotHaveAddress("This user = '" + userDTO + "' not have address.");
        }

        log.debug("************ deleteByUser() ---> isDeletedAddress = " + isDeletedAddress);

        return isDeletedAddress;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public AddressDTO findById(Long id) {

        // TODO: Terminar este metodo.

        return null;
    }

    /**
     * @param country
     * @return
     */
    @Override
    public Set<AddressDTO> findByCountry(String country) {

        // TODO: Terminar este metodo.

        return null;
    }

    /**
     * @param city
     * @return
     */
    @Override
    public Set<AddressDTO> findByCity(String city) {

        // TODO: Terminar este metodo.

        return null;
    }

    /**
     * @param street
     * @return
     */
    @Override
    public Set<AddressDTO> findByStreet(String street) {

        // TODO: Terminar este metodo.

        return null;
    }

    /**
     * @param houseNumber
     * @return
     */
    @Override
    public Set<AddressDTO> findByHouseNumber(String houseNumber) {

        // TODO: Terminar este metodo.

        return null;
    }

    /**
     * @param postCode
     * @return
     */
    @Override
    public Set<AddressDTO> findByPostCode(String postCode) {

        // TODO: Terminar este metodo.

        return null;
    }

    /**
     * @param userDTO
     * @return
     */
    @Override
    public Set<AddressDTO> findByUser(UserDTO userDTO) {

        // TODO: Terminar este metodo.

        return null;
    }

}